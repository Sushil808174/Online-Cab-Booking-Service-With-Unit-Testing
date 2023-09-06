package com.safar.repository;

import com.safar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	// find a user by email
    public Optional<Users> findByEmail(String email);

    // find all user by roll
    public List<Users> findAllByRole(String role);
}
