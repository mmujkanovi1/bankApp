package com.example.bank.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
  private String[] errorMessages;
  private Map<String, List<String>> detailerrors;
  private Throwable throwable;

  public InvalidInputException(final String message) {
    super(message);
    errorMessages = new String[]{message};
  }

  public InvalidInputException(final String message, final Map<String, List<String>> detailerrors) {
    super(message);
    errorMessages = new String[]{message};
    this.detailerrors = detailerrors;
  }

  public InvalidInputException(final String message, final Throwable ex) {
    super(message, ex);
    errorMessages = new String[]{message};
  }

  public InvalidInputException(final String[] errorMessages) {
    this.errorMessages = errorMessages;
  }

  public InvalidInputException(final String message, final String[] errorMessages) {
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

