package kr.ac.ssu.orderit.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableOrderParamMenuDto {
    private Integer menuId;
    private Integer qty;
}