package com.safar.controller;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.service.CabBookingService;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private CabBookingService cabBookingService;

    // Test endpoint
    @GetMapping("/users/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    // Endpoint to save a CabBooking
    @PostMapping("/users/saveCabBooking")
    public ResponseEntity<CabBooking> saveCabBookingHandler(@RequestBody CabBooking cabBooking, Authentication authentication) {
        // Get the email of the authenticated user
        String email = authentication.getName();
        log.info(email + " user email");

        // Get user details by email
        Users user = userService.getUserDetailsByEmail(email);

        // Insert CabBooking with user email
        CabBooking cabBooking1 = cabBookingService.insertCabBooking(cabBooking, user.getEmail());

        return new ResponseEntity<>(cabBooking1, HttpStatus.ACCEPTED);
    }

    // Endpoint to save a user (registration)
    @PostMapping("/users")
    public ResponseEntity<Users> saveCustomerHandler(@RequestBody Users user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set the user's role
        user.setRole("ROLE_" + user.getRole().toUpperCase());

        // Register the customer
        Users registeredUser = userService.registerCustomer(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.ACCEPTED);
    }

    // Endpoint to get a user by email
    @GetMapping("/users/{email}")
    public ResponseEntity<Users> getUserByEmailHandler(@PathVariable("email") String email) {
        // Get user details by email
        Users user = userService.getUserDetailsByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    // Endpoint to get all users
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsersHandler() {
        // Get all user details
        List<Users> users = userService.getAllUsersDetails();

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    // Endpoint to update user details by email
    @PatchMapping("/users/{email}")
    public ResponseEntity<Users> updateUserDetailsByEmailHandler(@PathVariable("email") String email, @RequestBody Users users) {
        // Update user details by email
        Users user = userService.updateUserDetailsByEmail(email, users);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    // Endpoint to delete a user by email
    @DeleteMapping("/users/{email}")
    public ResponseEntity<Users> deleteUserByEmailHandler(@PathVariable("email") String email) {
        // Delete user by email
        Users user = userService.deleteUserEmail(email);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    // Endpoint to get all users by role
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<Users>> getAllUsersDetailsByRoleHandler(@PathVariable("role") String role) {
        // Get all users by role
        List<Users> users = userService.getAllUsersDetailsByRole(role);

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    // Endpoint to get the logged-in customer's details
    @GetMapping("/signIn")
    public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth) {
        // Get the email of the logged-in user
        log.info(auth.getName());

        // Get user details by email
        Users loggedInUser = userService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(loggedInUser, HttpStatus.ACCEPTED);
    }
}
