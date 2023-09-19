package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.entity.Order;
import kr.ac.ssu.orderit.entity.TableSession;
import kr.ac.ssu.orderit.repository.OrderRepository;
import kr.ac.ssu.orderit.repository.TableSessionRepository;
import kr.ac.ssu.orderit.vo.TableVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TableService {
    private final TableSessionRepository tableSessionRepository;
    private final OrderRepository orderRepository;

    public List<TableVo> getTables(){
        ArrayList<TableVo> tableVos = new ArrayList<>(180);
        for(int i = 0; i < 180; i++){
            TableVo tableVo = TableVo.builder()
                    .tableSessionId(null)
                    .tableNo(i+1)
                    .createdAt(null)
                    .orderTotal(0)
                    .build();
            tableVos.add(tableVo);
        }

        List<TableSession> tableSessions = tableSessionRepository.findAllByDeletedAtIsNullOrderByTableNoAsc();
        for(TableSession tableSession : tableSessions){
            int orderTotal = orderRepository.sumPriceByTableSessionId(tableSession.getId());

            TableVo tableVo = TableVo.builder()
                    .tableSessionId(tableSession.getId())
                    .tableNo(tableSession.getTableNo())
                    .createdAt(tableSession.getCreatedAt().getTime())
                    .orderTotal(orderTotal)
                    .build();
            tableVos.set(tableSession.getTableNo()-1, tableVo);
        }

        return tableVos;
    }

    public void leave(Integer tableSessionId) throws Exception {
        Optional<TableSession> tableSessionOptional = tableSessionRepository.findByIdAndDeletedAtIsNull(tableSessionId);
        if(tableSessionOptional.isEmpty()){
            throw new Exception("Table session not existing.");
        }
        TableSession tableSession = tableSessionOptional.get();
        tableSession.setDeletedAt(new Timestamp(System.currentTimeMillis()));

        tableSessionRepository.save(tableSession);
    }
}
