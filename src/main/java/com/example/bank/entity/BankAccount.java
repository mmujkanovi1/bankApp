package com.example.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends  AuditingData{

  @Id
  @SequenceGenerator(
      name = "bank_account_sequence",
      sequenceName = "bank_account_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "bank_account_sequence"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer", referencedColumnName = "id")
  private Customer customer;

  private BigDecimal balance;

}
