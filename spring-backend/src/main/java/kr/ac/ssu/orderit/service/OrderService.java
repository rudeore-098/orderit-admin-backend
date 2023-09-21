package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.repository.OrderMenuRepository;
import kr.ac.ssu.orderit.repository.OrderRepository;
import kr.ac.ssu.orderit.repository.mapping.OrderMapping;
import kr.ac.ssu.orderit.repository.mapping.OrderMenuMapping;
import kr.ac.ssu.orderit.repository.mapping.TeamOrderMapping;
import kr.ac.ssu.orderit.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderMenuRepository orderMenuRepository;
    private final OrderRepository orderRepository;

    public List<OrderVo> getOrders(){
        ArrayList<OrderVo> orderVos = new ArrayList<>();

        List<OrderMapping> orders = orderRepository.findAllOrders();
        for(OrderMapping order : orders){
            OrderVo orderVo = OrderVo.builder()
                    .orderId(order.getOrderId())
                    .tableNo(order.getTableNo())
                    .createdAt(order.getCreatedAt().getTime())
                    .price(order.getPrice())
                    .build();

            ArrayList<OrderMenuVo> menus = new ArrayList<>();
            List<OrderMenuMapping> orderMenus = orderMenuRepository.findAllByOrderId(order.getOrderId());
            for(OrderMenuMapping orderMenu : orderMenus){
                OrderMenuVo orderMenuVo = OrderMenuVo.builder()
                        .title(orderMenu.getTitle())
                        .qty(orderMenu.getQty())
                        .status(orderMenu.getStatus())
                        .build();
                menus.add(orderMenuVo);
            }

            orderVo.setMenus(menus);
            orderVos.add(orderVo);
        }

        return orderVos;
    }

    public List<TeamOrderMapping> getTeamOrders(String team){
        List<TeamOrderMapping> teamOrders = orderMenuRepository.findAllTeamOrders(team);
        return teamOrders;
    }

    public void changeOrderStatus(Integer orderMenuIdx, Integer status){
        orderMenuRepository.updateTeamOrdersStatus(orderMenuIdx, status);
    }
}
