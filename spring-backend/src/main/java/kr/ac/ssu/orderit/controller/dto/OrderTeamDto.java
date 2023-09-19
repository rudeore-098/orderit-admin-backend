package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderTeamDto {
    @NotNull(message = "team is null.")
    @Pattern(regexp = "a|b|c")
    private String team;
}
