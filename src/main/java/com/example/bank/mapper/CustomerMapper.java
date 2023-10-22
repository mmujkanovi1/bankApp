package com.example.bank.mapper;

import com.example.bank.annotation.IgnoreCommonDataMapping;
import com.example.bank.entity.Customer;
import com.example.bank.request.SaveCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  @IgnoreCommonDataMapping
  Customer saveCustomerRequestToCustomer(SaveCustomerRequest saveCustomerRequest);
}
