package com.example.bank.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBankAccountResponse {

  private Long id;
  private String name;
  private BigDecimal balance;
  private Long customerId;
  private String customerName;

}
