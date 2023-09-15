package kr.ac.ssu.orderit.controller;

import jakarta.validation.Valid;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import kr.ac.ssu.orderit.controller.dto.TableLoginRequestDto;
import kr.ac.ssu.orderit.controller.dto.TableLoginResponseDto;
import kr.ac.ssu.orderit.controller.dto.TableMenuResponseDto;
import kr.ac.ssu.orderit.controller.dto.TableOrderRequestDto;
import kr.ac.ssu.orderit.entity.TableSession;
import kr.ac.ssu.orderit.exception.BankDepositNotFoundException;
import kr.ac.ssu.orderit.exception.MenuNotFoundException;
import kr.ac.ssu.orderit.service.TableService;
import kr.ac.ssu.orderit.service.dto.TableLoginReturnDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/table")
@RequiredArgsConstructor
@Slf4j
public class TableController {
    private final TableService tableService;
    private final StatusCode statusCode;

    /**
     * Table login (01)
     */
    @NotNull
    @PostMapping("/login")
    @ResponseBody
    public CommonResponse<TableLoginResponseDto> login(@NotNull @Valid @RequestBody TableLoginRequestDto tableLoginRequestDto){
        TableLoginReturnDto tableLoginReturnDto = tableService.tableLogin(tableLoginRequestDto.toTableLoginParamDto());
        return new CommonResponse<>(statusCode.SSU2010, tableLoginReturnDto.toTableLoginResponseDto(), statusCode.SSU2010_MSG);
    }

    /**
     * Table menu (02)
     */
    @NotNull
    @PostMapping("/menu")
    @ResponseBody
    public CommonResponse<TableMenuResponseDto> menu(){
        return new CommonResponse<>(statusCode.SSU2020, tableService.tableMenu().toTableMenuResponseDto(), statusCode.SSU2020_MSG);
    }

    /**
     * Table order (03)
     */
    @NotNull
    @PostMapping("/order")
    @ResponseBody
    public CommonResponse<Object> order(@NotNull @Valid @RequestBody TableOrderRequestDto tableOrderRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TableSession tableSession = (TableSession) authentication.getPrincipal();

        try {
            tableService.tableOrder(tableOrderRequestDto.toTableOrderParamDto(tableSession.getId()));
            return new CommonResponse<>(statusCode.SSU2030, null, statusCode.SSU2030_MSG);
        } catch (MenuNotFoundException e){
            return new CommonResponse<>(statusCode.SSU4030, null, statusCode.SSU4030_MSG);
        } catch (BankDepositNotFoundException e){
            return new CommonResponse<>(statusCode.SSU4031, null, statusCode.SSU4031_MSG);
        }

    }
}
