package com.example.bank;

import com.example.bank.entity.Customer;
import com.example.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

  private final CustomerRepository customerRepository;

  @Autowired
  public BankApplication(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(BankApplication.class, args);
  }

  @Override
  public void run(String... args) {

    customerRepository.save(new Customer(1L, "Arisha Barron"));
    customerRepository.save(new Customer(2L, "Branden Gibson"));
    customerRepository.save(new Customer(3L, "Rhonda Church"));
    customerRepository.save(new Customer(4L, "Georgina Hazel"));

  }
}
