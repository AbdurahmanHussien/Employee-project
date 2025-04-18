# Employee Project

## Overview

This project is built with **Spring Boot** and provides an **API** for managing employees. Each employee can have one or more emails associated with them. The application allows CRUD operations on employees and their emails.

## Features

- Create, update, retrieve, and delete employee records.
- Each employee can have multiple email addresses.
- The application provides endpoints to manage employees and their emails.

## Technologies Used

- **Spring Boot** for backend development.
- **Spring Data JPA** for database access.
- **Oracle Database**  for storage.
- **MapStruct** for object mapping between entities and DTOs.
- **Lombok** for reducing boilerplate code.
- **Validation** annotations for input validation.

## Endpoints

### Employee Endpoints

- **GET** `/api/Employees`  
  Retrieves a list of all employees.

- **GET** `/api/Employees/{id}`  
  Retrieves an employee by ID.

- **POST** `/api/Employees/createEmployee`  
  Creates a new employee.

- **PUT** `/api/Employees/updateEmployee`  
  Updates an existing employee by ID.

- **DELETE** `/api/Employees/deleteEmployee/{id}`  
  Deletes an employee by ID.

### Email Endpoints

- **GET** `/api/Emails`  
  Retrieves a list of all emails.

- **POST** `/api/Emails/createEmail`  
  Creates a new email for an employee.

- **PUT** `/api/Emails/updateEmail`  
  Updates an email by ID.

- **DELETE** `/api/Emails/deleteEmail/{id}`  
  Deletes an email by ID.

## How to Run the Project

### Prerequisites

Make sure you have the following installed:

- **Java 11 or above**
- **Maven** or **Gradle** (for building the project)
- **IDE** (such as IntelliJ IDEA or Eclipse)

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/AbdurahmanHussien/Employee-project.git
