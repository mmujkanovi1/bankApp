package com.example.bank.service.impl;

import com.example.bank.dto.GenericList;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.exception.ResourceNotFoundException;
import com.example.bank.mapper.BankAccountMapper;
import com.example.bank.repository.BankAccountRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.GetBankAccountResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;
  private final CustomerRepository customerRepository;

  @Autowired
  public BankAccountServiceImpl(final BankAccountRepository bankAccountRepository,
      final CustomerRepository customerRepository) {
    this.bankAccountRepository = bankAccountRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public IdResponse addBankAccount(SaveBankAccountRequest saveBankAccountRequest) {
    Customer customer = getResourceById(customerRepository, saveBankAccountRequest.getCustomerId(),"customer");
    BankAccount bankAccount = BankAccountMapper.INSTANCE.saveBankAccountRequestToBankAccount(
        saveBankAccountRequest,
        customer
    );
   Long savedBankAccountId = bankAccountRepository.save(bankAccount).getId();
    return new IdResponse(savedBankAccountId);
  }

  @Override
  public GetBalanceResponse getBalance(Long bankAccountId) {
    BankAccount bankAccount = getResourceById(bankAccountRepository, bankAccountId,"bank account");
    return new GetBalanceResponse(bankAccount.getBalance());
  }

  @Override
  public GenericList<GetBankAccountResponse> getAllBankAccount() {
    List<BankAccount> bankAccountList = bankAccountRepository.findAll();
    List<GetBankAccountResponse> bankAccountResponseList =
        BankAccountMapper.INSTANCE.bankAccountListToGetBankAccountResponseList(bankAccountList);
    return new GenericList<>(bankAccountResponseList);
  }

  private <T, ID> T getResourceById(final JpaRepository<T, ID> jpaRepository, ID id, final String resourceName) {
    return jpaRepository.findById(id).orElseThrow(() -> {
      throw new ResourceNotFoundException("Ne postoji " + resourceName + " sa id = " + id);
    });
  }

}
