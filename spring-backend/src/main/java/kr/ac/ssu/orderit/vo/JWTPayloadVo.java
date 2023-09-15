package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JWTPayloadVo {
    private Integer tableNo;
    private Integer sessionId;
}
