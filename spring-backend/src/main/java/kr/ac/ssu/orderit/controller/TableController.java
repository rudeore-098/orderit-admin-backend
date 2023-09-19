package kr.ac.ssu.orderit.controller;

import jakarta.validation.Valid;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import kr.ac.ssu.orderit.controller.dto.TableLeaveDto;
import kr.ac.ssu.orderit.service.TableService;
import kr.ac.ssu.orderit.vo.TableVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/table")
@RequiredArgsConstructor
@Slf4j
public class TableController {
    private final StatusCode statusCode;
    private final TableService tableService;

    @NotNull
    @PostMapping("")
    @ResponseBody
    public CommonResponse table(){
        List<TableVo> tableVos = tableService.getTables();
        return new CommonResponse(statusCode.SSU2000, tableVos, statusCode.SSU2000_MSG);
    }

    @NotNull
    @PostMapping("/leave")
    @ResponseBody
    public CommonResponse leave(@RequestBody @Valid TableLeaveDto tableLeaveDto){
        try {
            tableService.leave(tableLeaveDto.getTableSessionId());
            return new CommonResponse(statusCode.SSU2000, null, statusCode.SSU2000_MSG);
        } catch (Exception e){
            return new CommonResponse(statusCode.SSU4000, null, statusCode.SSU4000_MSG);
        }
    }
}
