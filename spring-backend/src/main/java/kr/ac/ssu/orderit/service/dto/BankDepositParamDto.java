package kr.ac.ssu.orderit.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDepositParamDto {
    private String key;
    private String from;
    private String content;
}
