package com.example.bank.mapper;

import com.example.bank.annotation.IgnoreCommonDataMapping;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.request.SaveBankAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

  BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

  @Mapping(target = "customer", source = "customer")
  @Mapping(target = "name", source = "saveBankAccountRequest.name")
  @IgnoreCommonDataMapping
  BankAccount saveBankAccountRequestToBankAccount(SaveBankAccountRequest saveBankAccountRequest, Customer customer);

}
