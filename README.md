# bankApp

Spring Boot sample app for banking with three entities that are located in postgreSQL database. This app contains the following routes:
* Create a new bank account for a customer, with an initial deposit amount. A
single customer may have multiple bank accounts.
* Transfer amounts between any two accounts, including those owned by
different customers.
* Retrieve balances for a given account.
* Retrieve transfer history for a given account
* And additionaly, create customer route, for testing purposes!

You can also run it in dev, prod and test environment. When the application is started in test environment, integration tests that use h2 memory database are run. With JaCoCo we secured that the application fails when code coverage is less than 85% on package level.

Once you start the application, the customer table will consist of the following lines:  
```bash
[
 {
 "id": 1,
 "name": "Arisha Barron"
 },
 {
 "id": 2,
 "name": "Branden Gibson"
 },
 {
 "id": 3,
 "name": "Rhonda Church"
 },
 {
 "id": 4,
 "name": "Georgina Hazel"
 }
]
```
### Requirements! ###
* Installed java 17 on your machine.
* Installed PostgresSQL on your machine and created database with name “bankDB” on port “5432” with credentials:
    * username: "postgres"
    * password: "postgres"
### How to install and run application? ###
1. Download jar file from the repository
2. Open cmd as administrator
3. Type: 
```bash
java -jar /path/to/jar/file.jar
```

#### How to run application on dev enviroment? ####
* Type in cmd: 
```bash
java -jar -Dspring.profiles.active=dev /path/to/jar/file.jar
```

#### How to run application on prod enviroment? ####
* Type in cmd:
```bash
java -jar -Dspring.profiles.active=prod /path/to/jar/file.jar
```

#### How to run application on test enviroment? ####

Type in cmd:

```bash
java -jar -Dspring.profiles.active=test /path/to/jar/file.jar
```
### REST API 
The REST API to the app is described below.

#### Create customer


##### Request:

```
POST /customer
```

Request body:

```
{
  "name": "string"
}
```

##### Response:

Response body:
```
{
  "id": 0
}
```

#### Create bank account for customer


##### Request:

```
POST /bank-account
```

Request body:

```
{
  "name": "string",
  "customerId": 0,
  "balance": 0
}
```

##### Response:

Response body:
```
{
  "id": 0
}
```

#### Get bank account balance

##### Request:

```
GET /bank-account/bankAccountId/balance
```
Parameter:
```
bankAccountId
```

##### Response:

Response body:
```
{
  "balance": 0
}
```

#### Create transaction from one to another bank account


##### Request:

```
POST /transaction
```

Request body:

```
{
  "fromAccountId": 0,
  "toAccountId": 0,
  "amount": 0
}
```

##### Response:

Response body:
```
{
  "id": 0
}
```

#### Get bank accounttransaction history

##### Request:

```
GET /transaction/bankAccountId/history
```
Parameter:
```
bankAccountId
```

##### Response:

Response body:
```
{
  "list": [
    {
      "id": 0,
      "fromAccountId": 0,
      "fromAccountName": "string",
      "toAccountId": 0,
      "toAccountName": "string",
      "amount": 0
    }
  ]
}

