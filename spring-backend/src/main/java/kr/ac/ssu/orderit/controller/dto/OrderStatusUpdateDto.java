package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    @NotNull(message = "orderMenuIdx is null.")
    private Integer orderMenuIdx;

    @NotNull(message = "status is null.")
    @Min(value = 1)
    @Max(value = 2)
    private Integer status;
}
