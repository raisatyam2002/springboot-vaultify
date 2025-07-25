package com.example.demo.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); 
    User findByUsername(String username);
}
