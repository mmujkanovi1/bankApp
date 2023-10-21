package com.example.bank.service;

import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.response.IdResponse;

public interface CustomerService {

  IdResponse addCustomer(SaveCustomerRequest saveCustomerRequest);
}
