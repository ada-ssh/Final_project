package com.dostavka.service;

import com.dostavka.domain.User;
import com.dostavka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService{
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public Optional<User> getUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserByName(String name){
        return userRepository.findUserByName(name).get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserById(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public String getRole(int id){
        return userRepository.getRole(id);
    }
}
