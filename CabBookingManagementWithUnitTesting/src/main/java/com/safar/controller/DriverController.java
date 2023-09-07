package com.safar.controller;

import com.safar.entity.Driver;
import com.safar.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ADMIN")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    
    /*

    {
        "driverName" : "amank",
        "email" : "amansingh@gmail.com",
        "password" : "aman123",
        "mobileNo" : "1234567880",
        "address" : "delhi",
        "licenceNo" : "1234561890",
        "rating" : 4.5,
        "newLocation" : "delhi",
        "status" : "Available",
        "car" : {
            "carType" : "SUV",
            "carNumber" : "UP 12 1234",
            "perKmRate" : 10.0
        }
    }

    "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0,
            "carNumber" : "1234567890",
            "carType" : "sedan",
            "carDescription" : "good"
        }


     */
    
    
    
    
    // Endpoint to insert a new driver
    @PostMapping("/driver")
    public ResponseEntity<Driver> insertDriverHandler(@Valid @RequestBody Driver driver) {
        // Encode the driver's password
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        // Insert the driver
        Driver insertedDriver = driverService.insertDriver(driver);
        return new ResponseEntity<>(insertedDriver, HttpStatus.CREATED);
    }

    // Endpoint to get all drivers
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDriversHandler() {
        // Get all drivers
        List<Driver> drivers = driverService.findAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.CREATED);
    }

    // Test endpoint
    @GetMapping("/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    // Endpoint to update driver details by email
    @PatchMapping("/driver/{email}")
    public ResponseEntity<Driver> updateDriverHandler(@PathVariable String email, @RequestBody Driver driver) {
        // Update driver details by email
        Driver updatedDriver = driverService.updateDriver(email, driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }

    // Endpoint to update driver's name by email
    @PatchMapping("/driver/{email}/{name}")
    public ResponseEntity<Driver> updateDriverHandler(@Valid @PathVariable String email, @PathVariable String name) {
        // Change driver's name by email
        Driver updatedDriver = driverService.changeName(email, name);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }

    // Endpoint to delete a driver by email
    @DeleteMapping("/driver/{driverEmail}")
    public ResponseEntity<String> deleteDriverHandler(@PathVariable String driverEmail) {
        // Delete driver by email
        String deletedDriver = driverService.deleteDriver(driverEmail);
        return new ResponseEntity<>(deletedDriver, HttpStatus.ACCEPTED);
    }

    // Endpoint to view the best drivers
    @GetMapping("/driver/bestdrivers")
    public ResponseEntity<List<Driver>> viewBestDriverListHandler() {
        // View the best drivers
        List<Driver> viewBestDrivers = driverService.viewBestDrivers();
        return new ResponseEntity<>(viewBestDrivers, HttpStatus.ACCEPTED);
    }

    // Endpoint to view driver details by email
    @GetMapping("/driver/{driverEmail}")
    public ResponseEntity<Driver> viewDriverByEmailHandler(@PathVariable String driverEmail) {
        // Get driver details by email
        Driver viewDriverById = driverService.getDriverDetailsByEmail(driverEmail);
        return new ResponseEntity<>(viewDriverById, HttpStatus.ACCEPTED);
    }
}
