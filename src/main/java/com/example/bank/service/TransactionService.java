package com.example.bank.service;

import com.example.bank.dto.GenericList;
import com.example.bank.request.SaveTransactionRequest;
import com.example.bank.response.GetTransactionResponse;
import com.example.bank.response.IdResponse;

public interface TransactionService {

  IdResponse makeTransaction(SaveTransactionRequest saveTransactionRequest);

  GenericList<GetTransactionResponse> getTransactionHistory(Long bankAccountId);
}
