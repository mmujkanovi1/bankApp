package com.example.bank.mapper;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.response.GetBankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BankAccountMapper {

  BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

  @Mapping(target = "customer", source = "customer")
  @Mapping(target = "name", source = "saveBankAccountRequest.name")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  BankAccount saveBankAccountRequestToBankAccount(SaveBankAccountRequest saveBankAccountRequest, Customer customer);

  List<GetBankAccountResponse> bankAccountListToGetBankAccountResponseList(List<BankAccount> bankAccountList);
}
