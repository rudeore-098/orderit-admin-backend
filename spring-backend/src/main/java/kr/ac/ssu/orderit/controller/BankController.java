package kr.ac.ssu.orderit.controller;

import jakarta.validation.Valid;
import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import kr.ac.ssu.orderit.controller.dto.BankAddDepositDto;
import kr.ac.ssu.orderit.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bank")
@RequiredArgsConstructor
@Slf4j
public class BankController {
    private final StatusCode statusCode;
    private final BankService bankService;

    @NotNull
    @PostMapping("/addDeposit")
    @ResponseBody
    public CommonResponse addDeposit(@RequestBody @Valid BankAddDepositDto bankAddDepositDto){
        bankService.addBankDeposit(bankAddDepositDto.getName(), bankAddDepositDto.getAmount(), bankAddDepositDto.getMemo());
        return new CommonResponse(statusCode.SSU2000, null, statusCode.SSU2000_MSG);
    }
}
