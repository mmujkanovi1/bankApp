package com.example.bank.util;

public final class Constants {

  public static final String API_VERSION_PREFIX = "/api/v1";

  public static final String INVALID_INPUT = "Invalid input";

  public static final String TRANSACTION_CREATE = "createTransaction";
  public static final String CUSTOMER_CREATE = "createCustomer";
  public static final String BANK_ACCOUNT_CREATE = "createBankAccount";

  public static final String FROM_ACCOUNT_NULL_VALIDATION_ERROR = "Wrong input, from account cannot be null";
  public static final String TO_ACCOUNT_NULL_VALIDATION_ERROR = "Wrong input, to account cannot be null";
  public static final String AMOUNT_NULL_VALIDATION_ERROR = "Wrong input, amount cannot be null";
  public static final String AMOUNT_LESS_THEN_ZERO = "Wrong input, amount cannot be less then zero";
  public static final String AMOUNT_GREATER_THEN_BALANCE = "Transaction amount can't be greater then bank account balance";
  public static final String FROM_ACCOUNT_TO_ACCOUNT_THE_SAME = "You have entered two identical bank accounts!";

  public static final String BALANCE_NULL_VALIDATION_ERROR = "Wrong input, balance cannot be null";
  public static final String CUSTOMER_ID_NULL_VALIDATION_ERROR = "Wrong input, customer id cannot be null";
  public static final String ACCOUNT_NAME_NULL_VALIDATION_ERROR = "Wrong input, bank account name cannot be null";
  public static final String BALANCE_LESS_THEN_ZERO = "Wrong input, balance cannot be less then zero";

  public static final String CUSTOMER_NAME_NULL_VALIDATION_ERROR = "Wrong input, customer name cannot be null";

  public static final String BANK_ACCOUNT_STRING = "Bank account";
  public static final String CUSTOMER_STRING = "Customer";
}
