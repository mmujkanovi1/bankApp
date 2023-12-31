package com.example.bank.service;

import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.IdResponse;

public interface BankAccountService {

  IdResponse addBankAccount(SaveBankAccountRequest saveBankAccountRequest);

  GetBalanceResponse getBalance(Long bankAccountId);

}
