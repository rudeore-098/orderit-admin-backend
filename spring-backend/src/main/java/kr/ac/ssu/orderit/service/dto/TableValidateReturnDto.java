package kr.ac.ssu.orderit.service.dto;

import kr.ac.ssu.orderit.entity.TableSession;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableValidateReturnDto {
    private TableSession tableSession;
}
