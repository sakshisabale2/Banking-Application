package com.bank.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.app.entity.User;
import com.bank.app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔥 REGISTER USER
    public User registerUser(User user) {

        if (user.getPassword() == null) {
            throw new RuntimeException("Password cannot be null");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }
    // GET USER
    public User getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

//package com.bank.app.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.bank.app.entity.User;
//import com.bank.app.repository.UserRepository;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public User getUser(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> 
//            new RuntimeException("User not found"));
//    }
//}