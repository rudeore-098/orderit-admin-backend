package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TeamOrderVo {
    private Integer orderId;
    private Integer status;
    private Integer tableNo;
    private Long createdAt;
    private List<TeamOrderMenuVo> menus;
}
