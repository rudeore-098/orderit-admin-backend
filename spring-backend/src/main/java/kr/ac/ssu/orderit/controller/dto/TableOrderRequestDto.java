package kr.ac.ssu.orderit.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.ac.ssu.orderit.service.dto.TableLoginParamDto;
import kr.ac.ssu.orderit.service.dto.TableOrderParamDto;
import kr.ac.ssu.orderit.service.dto.TableOrderParamMenuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TableOrderRequestDto {
    @NotNull(message = "menus is null.")
    @Valid
    private List<TableOrderRequestMenuDto> menus;

    @NotEmpty(message = "name is empty.")
    private String name;

    @org.jetbrains.annotations.NotNull
    public TableOrderParamDto toTableOrderParamDto(@org.jetbrains.annotations.NotNull Integer sessionId){
        ArrayList<TableOrderParamMenuDto> menus = new ArrayList<>();
        for(TableOrderRequestMenuDto menu : this.menus){
            menus.add(menu.toTableOrderParamMenuDto());
        }

        return TableOrderParamDto.builder()
                .menus(menus)
                .sessionId(sessionId)
                .name(this.name)
                .build();
    }


}
