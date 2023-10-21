package com.example.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AuditingData {

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }

}

