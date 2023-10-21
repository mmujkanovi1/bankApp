package com.example.bank.util;

import com.example.bank.error.ErrorResponse;
import com.example.bank.error.MessageSeverity;
import com.example.bank.exception.BusinessRuleException;
import com.example.bank.exception.InvalidInputException;
import com.example.bank.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final ResourceNotFoundException ex) {
    final HttpStatus status = HttpStatus.NOT_FOUND;
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
        .error(status.getReasonPhrase())
        .exception(ex.getClass().getTypeName())
        .message(ex.getMessage())
        .stackTrace(ex.getLocalizedMessage()).build());
  }

  @ExceptionHandler(value = InvalidInputException.class)
  public ResponseEntity<ErrorResponse> handleInvalidInputException(final InvalidInputException ex) {
    final HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
        .error(status.getReasonPhrase())
        .exception(ex.getClass().getTypeName())
        .message(ex.getMessage())
        .errorMessages(ex.getErrorMessages())
        .detailErrorsMap(ex.getDetailerrors())
        .stackTrace(ex.getLocalizedMessage()).build());
  }

  @ExceptionHandler(value = BusinessRuleException.class)
  public ResponseEntity<ErrorResponse> handleBusinessRuleException(final BusinessRuleException ex) {
    final HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).body(ErrorResponse.builder()
        .severity(MessageSeverity.ERROR)
        .error(status.getReasonPhrase())
        .exception(ex.getClass().getTypeName())
        .message(ex.getMessage())
        .errorMessages(ex.getErrorMessages())
        .detailErrorsMap(ex.getDetailerrors())
        .stackTrace(ex.getLocalizedMessage()).build());
  }

}
