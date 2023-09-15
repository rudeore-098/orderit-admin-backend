package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.entity.BankDeposit;
import kr.ac.ssu.orderit.repository.BankDepositRepository;
import kr.ac.ssu.orderit.service.dto.BankDepositParamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements BankService{
    private final BankDepositRepository bankDepositRepository;

    @Value("${spring.bank.deposit.key}")
    private String BANK_DEPOSIT_KEY;

    @Override
    public void bankDeposit(BankDepositParamDto bankDepositParamDto) {
        if(!bankDepositParamDto.getKey().equals(BANK_DEPOSIT_KEY)){
            log.warn("BANK_DEPOSIT_KEY does not match.");
            return;
        }

        if(!bankDepositParamDto.getFrom().equals("15885000")){
            log.warn("Message is not from 15885000.");
            return;
        }

        String content = bankDepositParamDto.getContent();

        int amount = Integer.parseInt(content.split("입금 ")[1].split("원")[0].replaceAll(",",""));
        String name = content.split("원\n")[1];

        BankDeposit bankDeposit = BankDeposit.builder()
                .amount(amount)
                .name(name)
                .status(0)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        bankDepositRepository.save(bankDeposit);
    }
}
