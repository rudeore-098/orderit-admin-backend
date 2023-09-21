package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.entity.BankDeposit;
import kr.ac.ssu.orderit.entity.TableSession;
import kr.ac.ssu.orderit.repository.BankDepositRepository;
import kr.ac.ssu.orderit.vo.TableVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankService {
    private final BankDepositRepository bankDepositRepository;
    public void addBankDeposit(String name, Integer amount, String memo){
        BankDeposit bankDeposit = BankDeposit.builder()
                .name(name)
                .amount(amount)
                .memo(memo)
                .status(0)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        bankDepositRepository.save(bankDeposit);
    }
}
