package com.example.bank.facade;

import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.response.IdResponse;
import com.example.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {

  private final CustomerService customerService;

  @Autowired
  public CustomerFacade(final CustomerService customerService) {
    this.customerService = customerService;
  }

  public IdResponse addCustomer(SaveCustomerRequest saveCustomerRequest) {
    return customerService.addCustomer(saveCustomerRequest);
  }
}
