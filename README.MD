# Documentation

Spring Boot personal project application with data JPA restful API, multiple role user authorization and Json Web Token (JWT) authentication

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
      "code": "number",
      "message": "string",
      "data": {
        "access_token": "string"
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
      "code": "number",
      "message": "string",
      "data": {
        "access_token": "string"
      } 
    }
    ```

### PROFILE AREA USER AND ADMIN

- Request
    - Method : GET
    - Endpoint : ``api/v1/me``
    - Header :
        - accept: application/json
        - Authorization: Bearer ``{access_token}``
- Response
    ```json
    {
      "code": "number",
      "message": "string",
      "data": {
        "id": "number",
        "name": "string",
        "email": "string",
        "role": "string"
      } 
    }
    ```

### ADMIN AREA

---

#### Get All Users

- Dummy Admin Data
  - email : ``admin@admin.com``
  - password : ``admin123``

- Request
    - Method : GET
    - Endpoint : ``api/v1/users``
    - Header :
        - accept: application/json
        - Authorization: Bearer ``{access_token}``
- Response
    ```json
    {
      "code": "number",
      "message": "string",
      "data": [
          {
            "id": "number",
            "name": "string",
            "email": "string",
            "role": "array"
          }
      ]
    }
    ```
  
##### Grant User As Admin

- Request
  - Method : POST
  - Endpoint : ``api/v1/grant``
  - Header :
    - accept: application/json
    - content-type: application/json
    - Authorization: Bearer ``{access_token}``
  - Body :
  ```json
    {
      "userId": "number"
    }
  ```
- Response
    ```json
    {
      "code": "number",
      "message": "string",
      "data": null
    }
    ```

##### UnGrant User As Admin

- Request
  - Method : POST
  - Endpoint : ``api/v1/ungrant``
  - Header :
    - accept: application/json
    - content-type: application/json
    - Authorization: Bearer ``{access_token}``
  - Body :
  ```json
    {
      "userId": "number"
    }
  ```
- Response
    ```json
    {
      "code": "number",
      "message": "string",
      "data": null
    }
    ```