package com.example.bank.service.impl;

import com.example.bank.entity.Customer;
import com.example.bank.exception.InvalidInputException;
import com.example.bank.mapper.CustomerMapper;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.response.IdResponse;
import com.example.bank.service.CustomerService;
import com.example.bank.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final String CUSTOMER_CREATE = "createCustomer";

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(final CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public IdResponse addCustomer(SaveCustomerRequest saveCustomerRequest) {
    Map<String, List<String>> invalidInputValidationErrors =
        getInvalidInputValidationErrors(
            saveCustomerRequest.getName()
        );
    if (!invalidInputValidationErrors.isEmpty()) {
      throw new InvalidInputException(Constants.INVALID_INPUT, invalidInputValidationErrors);
    }
    Customer customer = CustomerMapper.INSTANCE.saveCustomerRequestToCustomer(
        saveCustomerRequest
    );
    Long savedCustomerId = customerRepository.save(customer).getId();
    return new IdResponse(savedCustomerId);
  }

  private Map<String, List<String>> getInvalidInputValidationErrors(String name) {
    Map<String, List<String>> invalidInputValidationErrors = new HashMap<>();
    if (name == null) {
      invalidInputValidationErrors.computeIfAbsent(
          CUSTOMER_CREATE,
          (k) -> new ArrayList<>()
      ).add(Constants.CUSTOMER_NAME_NULL_VALIDATION_ERROR);
    }
    return invalidInputValidationErrors;
  }
}
