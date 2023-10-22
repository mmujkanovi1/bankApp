package com.example.bank.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTransactionResponse {

  private Long id;
  private Long fromAccountId;
  private String fromAccountName;
  private Long toAccountId;
  private String toAccountName;
  private BigDecimal amount;
  private LocalDateTime createdAt;

}
