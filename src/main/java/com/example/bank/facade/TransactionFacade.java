package com.example.bank.facade;

import com.example.bank.dto.GenericList;
import com.example.bank.request.SaveTransactionRequest;
import com.example.bank.response.GetTransactionResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionFacade {

  private final TransactionService transactionService;

  @Autowired
  public TransactionFacade(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  public IdResponse makeTransaction(SaveTransactionRequest saveTransactionRequest) {
  return transactionService.makeTransaction(saveTransactionRequest);
  }

  public GenericList<GetTransactionResponse> getTransactionHistory(Long bankAccountId) {
    return transactionService.getTransactionHistory(bankAccountId);
  }
}
