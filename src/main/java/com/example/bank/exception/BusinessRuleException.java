package com.example.bank.exception;

import java.util.List;
import java.util.Map;

public class BusinessRuleException extends RuntimeException {

  private String[] errorMessages;

  private Map<String, List<String>> detailerrors;
  private Throwable throwable;

  public BusinessRuleException(final String message) {
    super(message);
    errorMessages = new String[]{message};
  }

  public BusinessRuleException(final String message, final Map<String, List<String>> detailerrors) {
    super(message);
    errorMessages = new String[]{message};
    this.detailerrors = detailerrors;
  }

  public BusinessRuleException(final String message, final Throwable ex) {
    super(message, ex);
    errorMessages = new String[]{message};
  }

  public BusinessRuleException(final String[] errorMessages) {
    this.errorMessages = errorMessages;
  }

  public BusinessRuleException(final String message, final String[] errorMessages) {
    super(message);
    this.errorMessages = errorMessages;
  }

  public Map<String, List<String>> getDetailerrors() {
    return detailerrors;
  }

  public String[] getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(final String[] errorMessages) {
    this.errorMessages = errorMessages;
  }
}
