package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BankAddDepositDto {
    @NotEmpty(message = "name is null.")
    private String name;

    @NotNull(message = "amount is null.")
    @Min(value = 1)
    private Integer amount;

    @NotNull(message = "memo is null.")
    private String memo;
}
