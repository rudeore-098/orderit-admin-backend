package kr.ac.ssu.orderit.controller;

import jakarta.validation.Valid;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import kr.ac.ssu.orderit.controller.dto.BankDepositRequestDto;
import kr.ac.ssu.orderit.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bank")
@RequiredArgsConstructor
@Slf4j
public class BankController {
    private final BankService bankService;
    private final StatusCode statusCode;

    @NotNull
    @PostMapping("/deposit")
    @ResponseBody
    public CommonResponse deposit(@NotNull @RequestParam String key, @NotNull @Valid @RequestBody BankDepositRequestDto bankDepositRequestDto){
        bankService.bankDeposit(bankDepositRequestDto.toBankDepositParamDto(key));
        return new CommonResponse(statusCode.SSU2000, null, statusCode.SSU2000_MSG);
    }
}
