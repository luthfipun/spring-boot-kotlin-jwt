# Documentation

SpringBoot application for learning and personal project with basic JPA database restful API, multiple role user authorization and Json Web Token (JWT) authentication

## TechStack

 - Kotlin
 - SpringBoot
 - MySQL
 - Validation
 - JWT

## APIs

---

### REGISTER

- Request
  - Method : POST
  - Endpoint : ``api/v1/auth/register``
  - Header :
      - content-type: application/json
      - accept: application/json
  - Body :
    ```json
    {
      "name": "string",
      "email": "string",
      "password": "string"
    }
    ```
- Response
    ```json
    {
      "code": "integer",
      "message": "string",
      "data": {
        "token": "string"
      }   
    }
    ```

### LOGIN

- Request
    - Method : POST
    - Endpoint : ``api/v1/auth/login``
    - Header :
        - content-type: application/json
        - accept: application/json
    - Body :
      ```json
      {
        "email": "string",
        "password": "string"
      }
      ```
- Response
    ```json
    {
      "code": "integer",
      "message": "string",
      "data": {
        "token": "string"
      } 
    }
    ```

### USER AREA

- Request
    - Method : GET
    - Endpoint : ``api/v1/user``
    - Header :
        - accept: application/json
- Response
    ```json
    {
      "code": "integer",
      "message": "string",
      "data": {
        "id": "integer",
        "name": "string",
        "email": "string",
        "role": "string"
      } 
    }
    ```

### ADMIN AREA

- Dummy Admin Data
  - email : ``admin@admin.com``
  - password : ``admin123``

- Request
    - Method : GET
    - Endpoint : ``api/v1/admin``
    - Header :
        - accept: application/json
- Response
    ```json
    {
      "code": "integer",
      "message": "string",
      "data": [
          {
            "id": "integer",
            "name": "string",
            "email": "string",
            "role": "string"
          }
      ]
    }
    ```