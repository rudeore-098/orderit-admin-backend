package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.service.dto.BankDepositParamDto;

public interface BankService {
    void bankDeposit(BankDepositParamDto bankDepositParamDto);
}
