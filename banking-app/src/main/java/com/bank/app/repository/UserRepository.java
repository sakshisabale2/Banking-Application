package com.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}