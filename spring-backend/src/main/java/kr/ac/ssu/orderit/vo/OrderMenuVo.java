package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderMenuVo {
    private String title;
    private Integer qty;
    private Integer status;
}
