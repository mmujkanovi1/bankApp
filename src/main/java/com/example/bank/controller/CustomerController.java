package com.example.bank.controller;

import com.example.bank.facade.CustomerFacade;
import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.IdResponse;
import com.example.bank.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.API_VERSION_PREFIX + "/customer")
@Tag(name = "Customer")
public class CustomerController {

  private final CustomerFacade customerFacade;

  @Autowired
  public CustomerController(final CustomerFacade customerFacade) {
    this.customerFacade = customerFacade;
  }

  @PostMapping
  public ResponseEntity<IdResponse> addCustomer(
      @RequestBody final SaveCustomerRequest saveCustomerRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(customerFacade.addCustomer(saveCustomerRequest));
  }

}
