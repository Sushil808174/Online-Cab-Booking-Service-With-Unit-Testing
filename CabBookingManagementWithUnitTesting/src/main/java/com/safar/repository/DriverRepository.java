package com.safar.repository;


import com.safar.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer>, PagingAndSortingRepository<Driver,Integer> {

    public Optional<Driver> findByEmail(String email);
}
