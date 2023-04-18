package com.dostavka.service;

import com.dostavka.domain.Bucket;
import com.dostavka.domain.Role;
import com.dostavka.domain.User;
import com.dostavka.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;

    private List<User> users;

    @BeforeEach
    void setUser() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
        user = new User(1, "TestName", "testPassword" , "375290000000", "testEmail",0, Role.CLIENT, new Bucket());
        users = new ArrayList<>();
        users.add(user);
        userRepository.save(user);
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(users);
        Optional<List<User>> optional = Optional.ofNullable(userService.getAllUsers());
        assertTrue(optional.isPresent());
        verify(userRepository).findAll();
    }
}