package kr.ac.ssu.orderit.vo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Builder
@Data
public class JWTPayloadVo {
    @NotNull
    private Integer studentId;
    @NotNull
    private String name;
    @NotNull
    private String major;
}
