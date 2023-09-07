package com.safar.service;

import com.safar.entity.Users;
import com.safar.entity.Wallet;
import com.safar.exceptions.UsersException;

import java.util.List;

public interface UserService {

	 // Register a customer
    Users registerCustomer(Users customer);

    // Get user details by email
    Users getUserDetailsByEmail(String email) throws UsersException;

    // Get all user details
    List<Users> getAllUsersDetails() throws UsersException;

    // Update user details by email
    Users updateUserDetailsByEmail(String email, Users users) throws UsersException;

    // Delete a user by email
    Users deleteUserEmail(String email) throws UsersException;

    // Get all users by role
    List<Users> getAllUsersDetailsByRole(String role) throws UsersException;
}
