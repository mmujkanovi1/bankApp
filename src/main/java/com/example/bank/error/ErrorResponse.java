package com.example.bank.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
  private LocalDateTime timeStamp;
  @JsonIgnore
  private int status;
  private String error;
  private String message;
  private String path;
  private String exception;
  private String stackTrace;
  private MessageSeverity severity;
  private String[] errorMessages;
  private Map<String, List<String>> detailErrorsMap;
  private Long idAction;
}
