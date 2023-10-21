package com.example.bank.controller;

import com.example.bank.dto.GenericList;
import com.example.bank.facade.TransactionFacade;
import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.request.SaveTransactionRequest;
import com.example.bank.response.GetBalanceResponse;
import com.example.bank.response.GetTransactionResponse;
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
@RequestMapping(value = Constants.API_VERSION_PREFIX + "/transaction")
@Tag(name = "Transaction")
public class TransactionController {

  private final TransactionFacade transactionFacade;

  @Autowired
  public TransactionController(TransactionFacade transactionFacade) {
    this.transactionFacade = transactionFacade;
  }

  @PostMapping
  public ResponseEntity<IdResponse> makeTransaction(
      @RequestBody final SaveTransactionRequest saveTransactionRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionFacade.makeTransaction(saveTransactionRequest));
  }

  @GetMapping("/{bankAccountId}/history")
  public ResponseEntity<GenericList<GetTransactionResponse>> getTransactionHistory(
      @PathVariable final Long bankAccountId) {
    return ResponseEntity.status(HttpStatus.OK).body(transactionFacade.getTransactionHistory(bankAccountId));
  }



}
