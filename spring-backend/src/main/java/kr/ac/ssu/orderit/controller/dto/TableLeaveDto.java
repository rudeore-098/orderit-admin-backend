package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableLeaveDto {
    @NotNull(message = "tableSessionId is null.")
    private Integer tableSessionId;
}
