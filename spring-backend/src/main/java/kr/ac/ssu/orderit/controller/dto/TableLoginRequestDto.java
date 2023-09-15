package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kr.ac.ssu.orderit.service.dto.TableLoginParamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TableLoginRequestDto {
    @NotNull(message = "tableNo is null.")
    @Min(value = 1)
    @Max(value = 180)
    private Integer tableNo;

    @org.jetbrains.annotations.NotNull
    public TableLoginParamDto toTableLoginParamDto(){
        return TableLoginParamDto.builder()
                .tableNo(this.tableNo)
                .build();
    }
}
