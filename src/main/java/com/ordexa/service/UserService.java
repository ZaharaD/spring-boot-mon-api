package com.ordexa.service;

import com.ordexa.model.User;
import com.ordexa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() { return userRepository.findAll(); }
    public Optional<User> findById(Long id) { return userRepository.findById(id); }
    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }
    public User save(User user) { return userRepository.save(user); }
    public void delete(Long id) { userRepository.deleteById(id); }
} 