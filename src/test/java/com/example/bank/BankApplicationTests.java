package com.example.bank;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.example.bank.request.SaveBankAccountRequest;
import com.example.bank.request.SaveCustomerRequest;
import com.example.bank.request.SaveTransactionRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BankApplicationTests {

  @LocalServerPort
  private Integer port;

  private static final String HOST = "http://localhost:";

  private static final String API_VERSION_PREFIX = "/api/v1";

  private static final String CUSTOMER_PATH = "/customer";

  private static final String BANK_ACCOUNT_PATH = "/bank-account";

  private static final String BALANCE = "/{bankAccountId}/balance";

  private static final String TRANSACTION_PATH = "/transaction";

  private static final String TRANSACTION_HISTORY_PATH = "/{bankAccountId}/history";

  private static final String ID = "id";

  private static final String BANK_ACCOUNT_ID = "bankAccountId";

  private static final String AMOUNT = "amount";

  private static final String AMOUNT_FIRST_ELEMENT_OF_LIST = "list[0].amount";

  private static final String LIST = "list";

  private static final String balance = "balance";


  private SaveCustomerRequest saveCustomerRequestBody(String customerName) {
    SaveCustomerRequest saveCustomerRequest = new SaveCustomerRequest();
    saveCustomerRequest.setName(customerName);
    return saveCustomerRequest;
  }

  private SaveBankAccountRequest saveBankAccountRequestBody(String bankAccountName,
      Long customerId,
      BigDecimal balance) {
    SaveBankAccountRequest saveBankAccountRequest = new SaveBankAccountRequest();
    saveBankAccountRequest.setName(bankAccountName);
    saveBankAccountRequest.setCustomerId(customerId);
    saveBankAccountRequest.setBalance(balance);
    return saveBankAccountRequest;
  }

  private SaveTransactionRequest saveTransactionRequestBody(BigDecimal amount,
      Long fromAccountId,
      Long toAccountId) {
    SaveTransactionRequest saveTransactionRequest = new SaveTransactionRequest();
    saveTransactionRequest.setFromAccountId(fromAccountId);
    saveTransactionRequest.setToAccountId(toAccountId);
    saveTransactionRequest.setAmount(amount);
    return saveTransactionRequest;
  }

  private Integer createAndReturnCustomerId() {
    return RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveCustomerRequestBody("mirza"))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + CUSTOMER_PATH)
        .then()
        .extract()
        .path(ID);

  }

  private Integer createAndReturnBankAccountId(Long customerId,
      String bankAccountName,
      BigDecimal amount) {
    return RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveBankAccountRequestBody(
            "mirza second bank",
            customerId,
            BigDecimal.valueOf(50.00)))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH)
        .then()
        .extract()
        .path(ID);

  }

  @Test
  void contextLoads() {
  }

  @Test
  void shouldhaveStatus200() {
    Integer customerId = createAndReturnCustomerId();
    Integer firstBankAccountId = createAndReturnBankAccountId(Long.valueOf(customerId),
        "mirza first bank",
        BigDecimal.valueOf(50));
    Integer secondBankAccountId = createAndReturnBankAccountId(Long.valueOf(customerId),
        "mirza second bank",
        BigDecimal.valueOf(50));

    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .pathParam(
            BANK_ACCOUNT_ID,
            Long.valueOf(firstBankAccountId))
        .when()
        .get(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH + BALANCE)
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(balance, equalTo(Float.valueOf(50)));
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveTransactionRequestBody(
            BigDecimal.valueOf(20.00),
            Long.valueOf(firstBankAccountId),
            Long.valueOf(secondBankAccountId)))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + TRANSACTION_PATH)
        .then()
        .statusCode(HttpStatus.OK.value());
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .pathParam(
            BANK_ACCOUNT_ID,
            Long.valueOf(firstBankAccountId))
        .when()
        .get(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH + BALANCE)
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(balance, equalTo(Float.valueOf(50 - 20)));
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .pathParam(
            BANK_ACCOUNT_ID,
            Long.valueOf(firstBankAccountId))
        .when()
        .get(HOST + port + API_VERSION_PREFIX + TRANSACTION_PATH + TRANSACTION_HISTORY_PATH)
        .then()
        .statusCode(HttpStatus.OK.value())
        .body(AMOUNT_FIRST_ELEMENT_OF_LIST, equalTo(Float.valueOf(20)))
        .body(LIST, hasSize(1));

  }

  @Test
  void shouldThrowResourceNotFoundException() {
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .pathParam(
            BANK_ACCOUNT_ID,
            Long.valueOf(50))
        .when()
        .get(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH + BALANCE)
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void shouldThrowInvalidInputException() {
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveBankAccountRequestBody(
            null,
            null,
            null))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH)
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  void shouldThrowBusinessRuleException() {
    Integer customerId = createAndReturnCustomerId();
    Integer firstBankAccountId = createAndReturnBankAccountId(Long.valueOf(customerId),
        "mirza first bank",
        BigDecimal.valueOf(50));
    Integer secondBankAccountId = createAndReturnBankAccountId(Long.valueOf(customerId),
        "mirza second bank",
        BigDecimal.valueOf(50));
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveBankAccountRequestBody(
            "mirza bank account",
            Long.valueOf(customerId),
            BigDecimal.valueOf(-50)))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + BANK_ACCOUNT_PATH)
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
    RestAssured
        .given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(saveTransactionRequestBody(
            BigDecimal.valueOf(60.00),
            Long.valueOf(firstBankAccountId),
            Long.valueOf(secondBankAccountId)))
        .when()
        .post(HOST + port + API_VERSION_PREFIX + TRANSACTION_PATH)
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

  }


}
