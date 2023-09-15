package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kr.ac.ssu.orderit.service.dto.TableOrderParamMenuDto;
import lombok.Data;

@Data
public class TableOrderRequestMenuDto {
    @NotNull(message = "menuId is null.")
    private Integer menuId;

    @NotNull(message = "qty is null.")
    @Min(value = 1)
    private Integer qty;

    @org.jetbrains.annotations.NotNull
    public TableOrderParamMenuDto toTableOrderParamMenuDto(){
        return TableOrderParamMenuDto.builder()
                .menuId(this.menuId)
                .qty(this.qty)
                .build();
    }
}
