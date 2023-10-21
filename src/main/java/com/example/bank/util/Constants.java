package com.example.bank.util;

public final class Constants {

  public static final String API_VERSION_PREFIX = "/api/v1";

  public static final String INVALID_INPUT = "Invalid input";

  public static final String FROM_ACCOUNT_NULL_VALIDATION_ERROR = "Wrong input, from account cannot be null";
  public static final String TO_ACCOUNT_NULL_VALIDATION_ERROR = "Wrong input, to account cannot be null";
  public static final String AMOUNT_NULL_VALIDATION_ERROR = "Wrong input, amount cannot be null";
  public static final String AMOUNT_LESS_THEN_ZERO = "Wrong input, amount cannot be less then zero";
  public static final String AMOUNT_GREATER_THEN_BALANCE = "Transaction amount can't be greater then bank account balance";

}
