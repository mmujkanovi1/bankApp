package com.example.bank.service.impl;

import com.example.bank.dto.GenericList;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.mapper.BankAccountMapper;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.GetBankAccountResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.BankAccountService;
import com.example.bank.util.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

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

  @Override
  public GenericList<GetBankAccountResponse> getAllBankAccount() {
    List<BankAccount> bankAccountList =
        bankAccountRepository.findAll();
    List<GetBankAccountResponse> bankAccountResponseList =
        BankAccountMapper.INSTANCE.bankAccountListToGetBankAccountResponseList(
            bankAccountList
        );
    return new GenericList<>(bankAccountResponseList);
  }

}
