package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TableVo {
    private Integer tableSessionId;
    private Integer tableNo;
    private Long createdAt;
    private Integer orderTotal;
}
