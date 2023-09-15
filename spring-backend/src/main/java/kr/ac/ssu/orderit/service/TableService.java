package kr.ac.ssu.orderit.service;

import kr.ac.ssu.orderit.exception.BankDepositNotFoundException;
import kr.ac.ssu.orderit.exception.MenuNotFoundException;
import kr.ac.ssu.orderit.service.dto.*;

public interface TableService {
    TableLoginReturnDto tableLogin(TableLoginParamDto tableLoginParamDto);
    TableValidateReturnDto tableValidate(TableValidateParamDto tableValidateParamDto) throws Exception;
    TableMenuReturnDto tableMenu();
    void tableOrder(TableOrderParamDto tableOrderParamDto) throws MenuNotFoundException, BankDepositNotFoundException;
}
