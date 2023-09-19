package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.entity.OrderMenu;
import kr.ac.ssu.orderit.entity.TableSession;
import kr.ac.ssu.orderit.repository.OrderMenuRepository;
import kr.ac.ssu.orderit.repository.OrderRepository;
import kr.ac.ssu.orderit.repository.TableSessionRepository;
import kr.ac.ssu.orderit.repository.mapping.TeamOrderMapping;
import kr.ac.ssu.orderit.repository.mapping.TeamOrderMenuMapping;
import kr.ac.ssu.orderit.vo.TableVo;
import kr.ac.ssu.orderit.vo.TeamOrderMenuVo;
import kr.ac.ssu.orderit.vo.TeamOrderVo;
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
public class OrderService {
    private final OrderMenuRepository orderMenuRepository;

    public List<TeamOrderVo> getOrders(String team){
        ArrayList<TeamOrderVo> teamOrderVos = new ArrayList<>();

        List<TeamOrderMapping> teamOrders = orderMenuRepository.findAllTeamOrderIds(team);
        for(TeamOrderMapping teamOrder : teamOrders){
            TeamOrderVo teamOrderVo = TeamOrderVo.builder()
                    .orderId(teamOrder.getOrderId())
                    .status(teamOrder.getStatus())
                    .tableNo(teamOrder.getTableNo())
                    .createdAt(teamOrder.getCreatedAt().getTime())
                    .build();

            ArrayList<TeamOrderMenuVo> menus = new ArrayList<>();
            List<TeamOrderMenuMapping> orderMenus = orderMenuRepository.findAllTeamOrders(team, teamOrder.getOrderId());
            for(TeamOrderMenuMapping orderMenu : orderMenus){
                TeamOrderMenuVo teamOrderMenuVo = TeamOrderMenuVo.builder()
                        .title(orderMenu.getTitle())
                        .qty(orderMenu.getQty())
                        .build();
                menus.add(teamOrderMenuVo);
            }

            teamOrderVo.setMenus(menus);
            teamOrderVos.add(teamOrderVo);
        }

        return teamOrderVos;
    }

    public void changeOrderStatus(String team, Integer orderId, Integer status){
        orderMenuRepository.updateTeamOrdersStatus(team, orderId, status);
    }
}
