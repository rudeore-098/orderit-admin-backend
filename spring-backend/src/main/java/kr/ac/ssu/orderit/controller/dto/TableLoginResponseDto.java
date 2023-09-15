package kr.ac.ssu.orderit.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableLoginResponseDto {
    private String accessToken;
}
