package com.example.bank.util;

import com.example.bank.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GlobalService {

  public <T, ID> T getResourceById(final JpaRepository<T, ID> jpaRepository, ID id, final String resourceName) {
    return jpaRepository.findById(id).orElseThrow(() -> {
      throw new ResourceNotFoundException(resourceName + " with id = " + id + " does not exis!");
    });
  }

}
