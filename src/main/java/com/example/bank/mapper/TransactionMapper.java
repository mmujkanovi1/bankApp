package com.example.bank.mapper;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Transaction;
import com.example.bank.request.SaveTransactionRequest;
import com.example.bank.response.GetTransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  @Mapping(target = "fromAccount", source = "fromAccount")
  @Mapping(target = "toAccount", source = "toAccount")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  Transaction saveTransactionRequestToTransaction(SaveTransactionRequest saveTransactionRequest, BankAccount fromAccount,
      BankAccount toAccount);

  @Mapping(target = "fromAccountId", source = "fromAccount.id")
  @Mapping(target = "fromAccountName", source = "fromAccount.name")
  @Mapping(target = "toAccountId", source = "toAccount.id")
  @Mapping(target = "toAccountName", source = "toAccount.name")
  GetTransactionResponse transactionToGetTransactionResponse(Transaction transaction);

  List<GetTransactionResponse> transactionListToGetTransactionResponseList(List<Transaction> transactionList);
}
