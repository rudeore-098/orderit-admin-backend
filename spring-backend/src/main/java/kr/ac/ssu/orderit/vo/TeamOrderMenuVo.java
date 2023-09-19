package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeamOrderMenuVo {
    private String title;
    private Integer qty;
}
