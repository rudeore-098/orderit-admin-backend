package kr.ac.ssu.orderit.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TableOrderParamDto {
    private Integer sessionId;
    private List<TableOrderParamMenuDto> menus;
    private String name;
}
