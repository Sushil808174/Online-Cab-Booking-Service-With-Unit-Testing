# Online-Cab-Booking-Service-With-Unit-Testing

# This is an Online cab booking application called Safar Which provide rest api services for booking cab and for managing users with two roles admin and User with interactive api services.
## Modules
 - User Module
 - Cab Managment Module
 - Driver Managment Module
 - Booking Managment Module
 - Wallet Managment Module

## Backend Technologies
- Spring Boot
- Maven
- Spring Data Jpa
- Hibernate
- Spring Security
- Mysql
- Lombok
- Swagger


## ER DIGRAM
 ![SAFAR DIAGRAM](https://github.com/akt0001c/fearful-doll-6867/assets/115461689/59fbdd3b-f22e-41c7-87c2-faf1d330cf34)
  
  
## FEATURES
 - User can login ,signin and Book the cab.
 - Admin can manage All Users , Cab Drivers and their cab.
 

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
