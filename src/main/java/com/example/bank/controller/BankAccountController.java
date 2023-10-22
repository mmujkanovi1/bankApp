package com.example.bank.controller;

import com.example.bank.dto.GenericList;
import com.example.bank.facade.BankAccountFacade;
import com.example.bank.request.SaveBankAccountRequest;
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
@RequestMapping(value = Constants.API_VERSION_PREFIX + "/bank-account")
@Tag(name = "Bank account")
public class BankAccountController {

  private final BankAccountFacade bankAccountFacade;

  @Autowired
  public BankAccountController(final BankAccountFacade bankAccountFacade) {
    this.bankAccountFacade = bankAccountFacade;
  }

  @PostMapping
  public ResponseEntity<IdResponse> addBankAccount(
      @RequestBody final SaveBankAccountRequest saveBankAccountRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(bankAccountFacade.addBankAccount(saveBankAccountRequest));
  }

  @GetMapping("/{bankAccountId}/balance")
  public ResponseEntity<GetBalanceResponse> getBalance(
      @PathVariable final Long bankAccountId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(bankAccountFacade.getBalance(bankAccountId));
  }

}
