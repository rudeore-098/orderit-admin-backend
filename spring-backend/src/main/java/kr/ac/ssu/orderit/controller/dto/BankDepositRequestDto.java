package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import kr.ac.ssu.orderit.service.dto.BankDepositParamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
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
