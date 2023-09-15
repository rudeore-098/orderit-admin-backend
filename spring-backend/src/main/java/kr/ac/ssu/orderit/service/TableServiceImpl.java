package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.entity.*;
import kr.ac.ssu.orderit.exception.BankDepositNotFoundException;
import kr.ac.ssu.orderit.exception.MenuNotFoundException;
import kr.ac.ssu.orderit.provider.TokenProvider;
import kr.ac.ssu.orderit.repository.*;
import kr.ac.ssu.orderit.repository.mapping.MenuMapping;
import kr.ac.ssu.orderit.service.dto.*;
import kr.ac.ssu.orderit.vo.JWTPayloadVo;
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
public class TableServiceImpl implements TableService{
    private final TableSessionRepository tableSessionRepository;
    private final BankDepositRepository bankDepositRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final MenuRepository menuRepository;
    private final TokenProvider tokenProvider;

    @Override
    public TableLoginReturnDto tableLogin(TableLoginParamDto tableLoginParamDto) {
        Optional<TableSession> tableSessionOptional = tableSessionRepository.findByTableNoAndDeletedAtIsNull(tableLoginParamDto.getTableNo());
        TableSession tableSession;

        if(tableSessionOptional.isEmpty()){
            tableSession = TableSession.builder()
                    .tableNo(tableLoginParamDto.getTableNo())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            tableSession = tableSessionRepository.save(tableSession);
        }else{
            tableSession = tableSessionOptional.get();
        }

        JWTPayloadVo accessTokenPayload = JWTPayloadVo.builder()
                .tableNo(tableSession.getTableNo())
                .sessionId(tableSession.getId())
                .build();

        String accessToken = tokenProvider.generateAccessToken(accessTokenPayload);

        return TableLoginReturnDto.builder()
                .accessToken(accessToken)
                .build();

    }

    @Override
    public TableValidateReturnDto tableValidate(TableValidateParamDto tableValidateParamDto) throws Exception {
        JWTPayloadVo jwtPayloadVo;
        try {
            jwtPayloadVo = tokenProvider.validateAccessToken(tableValidateParamDto.getAccessToken());
        } catch (Exception e){
            throw new Exception("Failed to verify access token.");
        }

        Optional<TableSession> tableSessionOptional = tableSessionRepository.findByIdAndDeletedAtIsNull(jwtPayloadVo.getSessionId());
        if(tableSessionOptional.isEmpty()){
            throw new Exception("Failed to get table session.");
        }

        return TableValidateReturnDto.builder()
                .tableSession(tableSessionOptional.get())
                .build();
    }

    @Override
    public TableMenuReturnDto tableMenu() {
        List<MenuMapping> menus = menuRepository.findAllByIdIsNotNull();
        return TableMenuReturnDto.builder()
                .menus(menus)
                .build();
    }

    @Override
    public void tableOrder(TableOrderParamDto tableOrderParamDto) throws MenuNotFoundException, BankDepositNotFoundException {
        int totalPrice = 0;
        List<OrderMenu> orderMenus = new ArrayList<>();

        for(TableOrderParamMenuDto paramMenu : tableOrderParamDto.getMenus()){
            Optional<Menu> menuOptional = menuRepository.findByIdAndIsAvail(paramMenu.getMenuId(), 1);
            if(menuOptional.isEmpty()){
                throw new MenuNotFoundException();
            }

            Menu menu = menuOptional.get();
            totalPrice += menu.getPrice() * paramMenu.getQty();

            OrderMenu orderMenu = OrderMenu.builder()
                    .menuId(menu.getId())
                    .qty(paramMenu.getQty())
                    .status(0)
                    .build();
            orderMenus.add(orderMenu);
        }

        Optional<BankDeposit> bankDepositOptional = bankDepositRepository.findByNameAndAmountAndStatus(tableOrderParamDto.getName(), totalPrice, 0);
        if(bankDepositOptional.isEmpty()){
            throw new BankDepositNotFoundException();
        }
        BankDeposit bankDeposit = bankDepositOptional.get();
        bankDeposit.setStatus(1);
        bankDepositRepository.save(bankDeposit);

        Order order = Order.builder()
                .tableSessionId(tableOrderParamDto.getSessionId())
                .price(totalPrice)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        order = orderRepository.save(order);

        for(OrderMenu orderMenu : orderMenus){
            orderMenu.setOrderId(order.getId());
            orderMenuRepository.save(orderMenu);
        }

    }
}
