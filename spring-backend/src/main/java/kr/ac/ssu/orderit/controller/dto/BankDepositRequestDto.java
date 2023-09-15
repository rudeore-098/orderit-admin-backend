package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.ac.ssu.orderit.service.dto.BankDepositParamDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDepositRequestDto {
    @NotEmpty(message = "from is empty.")
    private String from;

    @NotEmpty(message = "content is empty.")
    private String content;

    @org.jetbrains.annotations.NotNull
    public BankDepositParamDto toBankDepositParamDto(@org.jetbrains.annotations.NotNull String key){
        return BankDepositParamDto.builder()
                .key(key)
                .from(this.from)
                .content(this.content)
                .build();
    }
}
