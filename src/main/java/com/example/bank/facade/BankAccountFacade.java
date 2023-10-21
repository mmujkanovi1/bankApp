package com.example.bank.facade;

import com.example.bank.dto.GenericList;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.GetBankAccountResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.GeneralPath;

@Service
public class BankAccountFacade {

  private final BankAccountService bankAccountService;

  @Autowired
  public BankAccountFacade(final BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  public IdResponse addBankAccount(SaveBankAccountRequest saveBankAccountRequest) {
    return bankAccountService.addBankAccount(saveBankAccountRequest);
  }

  public GetBalanceResponse getBalance(Long bankAccountId) {
    return bankAccountService.getBalance(bankAccountId);
  }

  public GenericList<GetBankAccountResponse> getAllBankAccount() {
    return bankAccountService.getAllBankAccount();
  }
}
