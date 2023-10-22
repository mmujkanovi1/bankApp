package com.example.bank.service.impl;

import com.example.bank.dto.GenericList;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Transaction;
import com.example.bank.exception.BusinessRuleException;
import com.example.bank.exception.InvalidInputException;
import com.example.bank.mapper.TransactionMapper;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.request.SaveTransactionRequest;
import com.example.bank.response.GetTransactionResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.TransactionService;
import com.example.bank.util.Constants;
import com.example.bank.util.GlobalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

  private static final String TRANSACTION_CREATE = "createTransaction";

  private final TransactionRepository transactionRepository;
  private final BankAccountRepository bankAccountRepository;
  private final GlobalService globalService;

  public TransactionServiceImpl(final TransactionRepository transactionRepository,
      final BankAccountRepository bankAccountRepository,
      final GlobalService globalService) {
    this.transactionRepository = transactionRepository;
    this.bankAccountRepository = bankAccountRepository;
    this.globalService = globalService;
  }

  @Override
  public IdResponse makeTransaction(SaveTransactionRequest saveTransactionRequest) {
    Map<String, List<String>> invalidInputValidationErrors =
        getInvalidInputValidationErrors(
            saveTransactionRequest.getFromAccountId(),
            saveTransactionRequest.getToAccountId(),
            saveTransactionRequest.getAmount()
        );
    if (!invalidInputValidationErrors.isEmpty()) {
      throw new InvalidInputException(Constants.INVALID_INPUT, invalidInputValidationErrors);
    }
    BankAccount fromAccount =
        globalService.getResourceById(
            bankAccountRepository,
            saveTransactionRequest.getFromAccountId(),
            "Bank account"
        );
    BankAccount toAccount =
        globalService.getResourceById(
            bankAccountRepository,
            saveTransactionRequest.getToAccountId(),
            "Bank account"
        );

    Map<String, List<String>> businessRuleValidationErrors =
        getBusinessRuleValidationErrors(
            saveTransactionRequest.getAmount(),
            fromAccount.getBalance()
        );
    if (!businessRuleValidationErrors.isEmpty()) {
      throw new BusinessRuleException(Constants.INVALID_INPUT, businessRuleValidationErrors);
    }

    Transaction transaction = TransactionMapper.INSTANCE
        .saveTransactionRequestToTransaction(
            saveTransactionRequest,
            fromAccount,
            toAccount
        );

    fromAccount.setBalance(
        fromAccount.getBalance().subtract(
            saveTransactionRequest.getAmount()
        )
    );
    bankAccountRepository.save(fromAccount);

    toAccount.setBalance(
        toAccount.getBalance().add(
            saveTransactionRequest.getAmount()
        )
    );
    bankAccountRepository.save(toAccount);

    Long savedTransactionId = transactionRepository.save(transaction).getId();
    return new IdResponse(savedTransactionId);
  }

  @Override
  public GenericList<GetTransactionResponse> getTransactionHistory(Long bankAccountId) {
    List<Transaction> transactionList =
        transactionRepository.findByFromAccountIdOrToAccountIdOrderById(
            bankAccountId,
            bankAccountId
        );
    List<GetTransactionResponse> transactionResponseList =
        TransactionMapper.INSTANCE
            .transactionListToGetTransactionResponseList(transactionList);

    return new GenericList<>(transactionResponseList);
  }

  private Map<String, List<String>> getInvalidInputValidationErrors(Long fromAccount,
      Long toAccount,
      BigDecimal amount) {
    Map<String, List<String>> invalidInputValidationErrors = new HashMap<>();
    if (fromAccount == null) {
      invalidInputValidationErrors.computeIfAbsent(
          TRANSACTION_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.FROM_ACCOUNT_NULL_VALIDATION_ERROR);
    }
    if (toAccount == null) {
      invalidInputValidationErrors.computeIfAbsent(
          TRANSACTION_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.TO_ACCOUNT_NULL_VALIDATION_ERROR);
    }
    if (amount == null) {
      invalidInputValidationErrors.computeIfAbsent(
          TRANSACTION_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.AMOUNT_NULL_VALIDATION_ERROR);
    }
    return invalidInputValidationErrors;
  }

  private Map<String, List<String>> getBusinessRuleValidationErrors(BigDecimal amount,
      BigDecimal fromAccountBalance) {
    Map<String, List<String>> businessRuleValidationErrors = new HashMap<>();
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      businessRuleValidationErrors.computeIfAbsent(
          TRANSACTION_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.AMOUNT_LESS_THEN_ZERO);
    } else if (amount.compareTo(fromAccountBalance) > 0) {
      businessRuleValidationErrors.computeIfAbsent(
          TRANSACTION_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.AMOUNT_GREATER_THEN_BALANCE);
    }
    return businessRuleValidationErrors;
  }

}
