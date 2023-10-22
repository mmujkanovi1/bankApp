package com.example.bank.facade;

import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
