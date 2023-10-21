package com.example.bank.service.impl;

import com.example.bank.entity.Customer;
import com.example.bank.mapper.CustomerMapper;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.response.IdResponse;
import com.example.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(final CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public IdResponse addCustomer(SaveCustomerRequest saveCustomerRequest) {
    Customer customer = CustomerMapper.INSTANCE.saveCustomerRequestToCustomer(
          saveCustomerRequest
    );
    Long savedCustomerId = customerRepository.save(customer).getId();
    return new IdResponse(savedCustomerId);
  }
}
