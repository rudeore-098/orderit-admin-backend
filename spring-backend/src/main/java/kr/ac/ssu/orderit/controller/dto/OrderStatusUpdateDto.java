package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    @NotNull(message = "team is null.")
    @Pattern(regexp = "a|b|c")
    private String team;

    @NotNull(message = "orderId is null.")
    private Integer orderId;

    @NotNull(message = "status is null.")
    @Min(value = 1)
    @Max(value = 2)
    private Integer status;
}
