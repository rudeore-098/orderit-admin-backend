package kr.ac.ssu.orderit.controller;

import jakarta.validation.Valid;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import kr.ac.ssu.orderit.controller.dto.OrderStatusUpdateDto;
import kr.ac.ssu.orderit.controller.dto.OrderTeamDto;
import kr.ac.ssu.orderit.controller.dto.TableLeaveDto;
import kr.ac.ssu.orderit.service.OrderService;
import kr.ac.ssu.orderit.service.TableService;
import kr.ac.ssu.orderit.vo.TableVo;
import kr.ac.ssu.orderit.vo.TeamOrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final StatusCode statusCode;
    private final OrderService orderService;

    @NotNull
    @PostMapping("/team")
    @ResponseBody
    public CommonResponse team(@RequestBody @Valid OrderTeamDto orderTeamDto){
        List<TeamOrderVo> teamOrderVos = orderService.getOrders(orderTeamDto.getTeam());
        return new CommonResponse(statusCode.SSU2000, teamOrderVos, statusCode.SSU2000_MSG);
    }

    @NotNull
    @PostMapping("/statusUpdate")
    @ResponseBody
    public CommonResponse statusUpdate(@RequestBody @Valid OrderStatusUpdateDto orderStatusUpdateDto){
        orderService.changeOrderStatus(orderStatusUpdateDto.getTeam(),
                orderStatusUpdateDto.getOrderId(),
                orderStatusUpdateDto.getStatus());

        return new CommonResponse(statusCode.SSU2000, null, statusCode.SSU2000_MSG);
    }
}