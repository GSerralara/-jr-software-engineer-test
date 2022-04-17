# Commerce Services - Technical Interview

## Context:

In this test, I have done the backend development part


## Dev part

### Problem:

We are an online book store. We receive book orders from customers and process them.

### Features

- **Create a new Order**:
  - The application receives orders in a JSON format through an HTTP API endpoint (POST).
  - Orders contain a list of books and the quantity.
  - Before registering the order, the system should check if there's enough stock to fulfill the order (`import.sql` will set the initial stock).
  - If one of the books in the order does not have enough stock we will reject the entire order.
  - After stock validation, the order is marked as a success, and it would return a Unique Order Identifier to the caller of the HTTP API endpoint.
  - If the order was processed we need to update available stock, taking into consideration:
    - Updating stock should not be a blocker for replying to the customer.
    - If the process of updating stock fails, should not cause an error in order processing.

- **Retrieve Orders**:
  - The application has an endpoint to extract a list of existing orders. So that we can run "curl localhost:8080/orders/" and get a list of them

### Required:

- Resolution needs to be fully in English
- You need to use Java 11
- You are provided with a scaffold, fork or create a public repository with your solution. Once the code is complete, reply to your hiring person of contact.
- We expect you to implement tests for the requested functionalities. You decide the scope.
### Documentation
* [Documentation in Javadoc Format of the project](JavaDoc/index.html)
* [Updated help links with used one](HELP.md)
### How to run

Building
```shell
$ ./mvnw compile
```

Test
```shell
$ ./mvnw test
```

Start the application

```shell
$ ./mvnw spring-boot:run
```

Getting current stock for a given book 

```shell
$ curl localhost:8080/books_stock/ae1666d6-6100-4ef0-9037-b45dd0d5bb0e
Answer
{"id":"ae1666d6-6100-4ef0-9037-b45dd0d5bb0e","name":"adipisicing culpa Lorem laboris adipisicing","quantity":0}
```

Create a new Order
```shell
$ curl -X POST http://localhost:8080/orders/saveOrder
-H "Content-Type: application/json"
-d '[
    {
        "id": "22d580fc-d02e-4f70-9980-f9693c18f6e0",
        "name": "dolore aliqua sint ipsum laboris",
        "quantity":2
    }
]'
Answer:
{
    "id": "0707e886-f263-4f25-86f6-d86018939bf2",
    "booklist": [
        {
            "id": "22d580fc-d02e-4f70-9980-f9693c18f6e0",
            "name": "dolore aliqua sint ipsum laboris"
        },
        {
            "id": "22d580fc-d02e-4f70-9980-f9693c18f6e0",
            "name": "dolore aliqua sint ipsum laboris"
        }
    ]
}
```
Get List of orders
```shell
$ curl http://localhost:8080/orders/

Answer:
[
    {
        "id": "0707e886-f263-4f25-86f6-d86018939bf2",
        "booklist": [
            {
                "id": "22d580fc-d02e-4f70-9980-f9693c18f6e0",
                "name": "dolore aliqua sint ipsum laboris"
            },
            {
                "id": "22d580fc-d02e-4f70-9980-f9693c18f6e0",
                "name": "dolore aliqua sint ipsum laboris"
            }
        ]
    }
]
```