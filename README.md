# Online Cab Booking Application

# This is an Online cab booking application called Safar Which provide rest api services for booking cab and for managing users with two roles admin and User with interactive api services.
## Modules
 - User Module
 - Cab Managment Module
 - Driver Managment Module
 - Booking Managment Module
 - Wallet Managment Module

## Technologies Used

List the technologies and tools used in your project. You can use badges to make it visually appealing.

- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-VERSION-brightgreen)
- ![Maven](https://img.shields.io/badge/Maven-VERSION-blue)
- ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-VERSION-yellow)
- ![Hibernate](https://img.shields.io/badge/Hibernate-VERSION-orange)
- ![Spring Security](https://img.shields.io/badge/Spring%20Security-VERSION-brightgreen)
- ![MySQL](https://img.shields.io/badge/MySQL-VERSION-blue)
- ![Lombok](https://img.shields.io/badge/Lombok-VERSION-orange)
- ![Swagger](https://img.shields.io/badge/Swagger-VERSION-brightgreen)


## ER DIGRAM
 ![SAFAR DIAGRAM](https://github.com/akt0001c/fearful-doll-6867/assets/115461689/59fbdd3b-f22e-41c7-87c2-faf1d330cf34)
  
  
## Features

Highlight the key features of your cab booking application.

- User registration and login
- Cab booking and management
- Driver management
- Admin dashboard
- Payment processing
- ...

## API Root Endpoint

`https://localhost:8888/`

`http://localhost:8888/swagger-ui/`


## API Module Endpoints

### Login Module

* `POST //signIn` : Admin can login with Email and password provided at the time of registration




### Sample API Response for Admin Login

`POST   localhost:8888/signIn`

* Request Body

```
    {
        "username": "Admin@gmail.com",
        "password": "admin"
    }

```
