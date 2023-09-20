package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderVo {
    private Integer orderId;
    private Integer tableNo;
    private Integer price;
    private Long createdAt;
    private List<OrderMenuVo> menus;
}
