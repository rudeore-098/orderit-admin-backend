package kr.ac.ssu.orderit.controller.dto;

import kr.ac.ssu.orderit.entity.Menu;
import kr.ac.ssu.orderit.repository.mapping.MenuMapping;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TableMenuResponseDto {
    private List<MenuMapping> menus;
}
