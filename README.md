# Employee Management API

## Overview
This API provides endpoints for managing employees, rating categories, and performing bell curve calculations.

## API Endpoints

### Employee Endpoints
- **POST** `/api/employees/save` – Create an employee.
- **PUT** `/api/employees/{id}` – Update an employee.
- **DELETE** `/api/employees/{id}` – Delete an employee.
- **GET** `/api/employees` – Fetch all employees.

### Rating Category Endpoints
- **POST** `/api/employees/rating-categories` – Create a rating category.
- **PUT** `/api/employees/rating-categories/{category}` – Update a rating category.
- **DELETE** `/api/employees/rating-categories/{category}` – Delete a rating category.
- **GET** `/api/employees/rating-categories` – Fetch all rating categories.

### Bell Curve Calculation Endpoints
- **GET** `/api/employees/actual-percentages` – Get actual percentages.
- **GET** `/api/employees/deviation` – Get deviations.
- **GET** `/api/employees/suggest-adjustments` – Suggest adjustments.
- **GET** `/api/employees/test-ratings` – Test ratings count (for debugging).


## Server Configuration
- **Port Number:** `7060`
- **Database Configuration:**
  - The `pom.xml` file contains Clever Cloud database properties.
  - Update the database configurations to connect with your local MySQL database or use cellcurve.sql which is in the root repository.
  

## Setup Instructions
1. Clone the repository.
2. Update `application.properties` with your local MySQL database credentials.
3. Right-click on the project main directory and select **Run As → Spring Boot App**.
4. The API will be accessible at `http://localhost:7060`.

## Dependencies
- Spring Boot
- MySQL
- Maven

## License
This project is open-source.

