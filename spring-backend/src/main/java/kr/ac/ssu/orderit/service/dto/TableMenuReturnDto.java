package kr.ac.ssu.orderit.service.dto;

import kr.ac.ssu.orderit.controller.dto.TableMenuResponseDto;
import kr.ac.ssu.orderit.entity.Menu;
import kr.ac.ssu.orderit.entity.TableSession;
import kr.ac.ssu.orderit.repository.mapping.MenuMapping;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@Builder
public class TableMenuReturnDto {
    private List<MenuMapping> menus;

    @NotNull
    public TableMenuResponseDto toTableMenuResponseDto(){
        return TableMenuResponseDto.builder()
                .menus(this.menus)
                .build();
    }
}
