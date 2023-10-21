package com.example.bank.request;

import com.example.bank.entity.BankAccount;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveTransactionRequest {

  private Long fromAccountId;
  private Long toAccountId;
  private BigDecimal amount;

}
