package com.example.bank.service.impl;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.exception.BusinessRuleException;
import com.example.bank.exception.InvalidInputException;
import com.example.bank.mapper.BankAccountMapper;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.BankAccountService;
import com.example.bank.util.Constants;
import com.example.bank.util.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  private static final String BANK_ACCOUNT_CREATE = "createBankAccount";

  private final BankAccountRepository bankAccountRepository;
  private final CustomerRepository customerRepository;
  private final GlobalService globalService;

  @Autowired
  public BankAccountServiceImpl(final BankAccountRepository bankAccountRepository,
      final CustomerRepository customerRepository,
      final GlobalService globalService) {
    this.bankAccountRepository = bankAccountRepository;
    this.customerRepository = customerRepository;
    this.globalService = globalService;
  }

  @Override
  public IdResponse addBankAccount(SaveBankAccountRequest saveBankAccountRequest) {
    Map<String, List<String>> invalidInputValidationErrors =
        getInvalidInputValidationErrors(
            saveBankAccountRequest.getBalance(),
            saveBankAccountRequest.getCustomerId(),
            saveBankAccountRequest.getName()
        );
    if (!invalidInputValidationErrors.isEmpty()) {
      throw new InvalidInputException(Constants.INVALID_INPUT, invalidInputValidationErrors);
    }

    Map<String, List<String>> businessRuleValidationErrors =
        getBusinessRuleValidationErrors(
            saveBankAccountRequest.getBalance()
        );
    if (!businessRuleValidationErrors.isEmpty()) {
      throw new BusinessRuleException(Constants.INVALID_INPUT, businessRuleValidationErrors);
    }

    Customer customer =
        globalService.getResourceById(
            customerRepository,
            saveBankAccountRequest.getCustomerId(),
            "Customer"
        );
    BankAccount bankAccount =
        BankAccountMapper.INSTANCE.saveBankAccountRequestToBankAccount(
            saveBankAccountRequest,
            customer
        );
    Long savedBankAccountId =
        bankAccountRepository.save(bankAccount).getId();
    return new IdResponse(savedBankAccountId);
  }

  private Map<String, List<String>> getInvalidInputValidationErrors(BigDecimal balance,
      Long customerId,
      String name) {
    Map<String, List<String>> invalidInputValidationErrors = new HashMap<>();
    if (balance == null) {
      invalidInputValidationErrors.computeIfAbsent(
          BANK_ACCOUNT_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.BALANCE_NULL_VALIDATION_ERROR);
    }
    if (customerId == null) {
      invalidInputValidationErrors.computeIfAbsent(
          BANK_ACCOUNT_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.CUSTOMER_ID_NULL_VALIDATION_ERROR);
    }
    if (name == null) {
      invalidInputValidationErrors.computeIfAbsent(
          BANK_ACCOUNT_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.ACCOUNT_NAME_NULL_VALIDATION_ERROR);
    }
    return invalidInputValidationErrors;
  }

  private Map<String, List<String>> getBusinessRuleValidationErrors(BigDecimal balance) {
    Map<String, List<String>> businessRuleValidationErrors = new HashMap<>();
    if (balance.compareTo(BigDecimal.ZERO) < 0) {
      businessRuleValidationErrors.computeIfAbsent(
          BANK_ACCOUNT_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.BALANCE_LESS_THEN_ZERO);
    }
    return businessRuleValidationErrors;
  }

  @Override
  public GetBalanceResponse getBalance(Long bankAccountId) {
    BankAccount bankAccount =
        globalService.getResourceById(
            bankAccountRepository,
            bankAccountId,
            "Bank account"
        );
    return new GetBalanceResponse(bankAccount.getBalance());
  }

}
