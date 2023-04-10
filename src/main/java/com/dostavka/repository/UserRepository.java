package com.dostavka.repository;

import com.dostavka.domain.Role;
import com.dostavka.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE u.name=:name")
    Optional<User> findUserByName(String name);

    Optional<User> findUserByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT role FROM User u WHERE u.login=:login")
    String getRole(int login);



}

