package kr.ac.ssu.orderit.service.dto;

import kr.ac.ssu.orderit.controller.dto.TableLoginResponseDto;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class TableLoginReturnDto {
    private String accessToken;

    @NotNull
    public TableLoginResponseDto toTableLoginResponseDto(){
        return TableLoginResponseDto.builder()
                .accessToken(this.accessToken)
                .build();
    }
}
