package com.dostavka.security;

import com.dostavka.domain.User;
import com.dostavka.domain.request.JwtAuthRequest;
import com.dostavka.domain.request.RegistrationUser;
import com.dostavka.repository.UserRepository;
import com.dostavka.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String getToken(JwtAuthRequest jwtAuthRequest){
        Optional<User> user = userRepository.findUserByLogin(jwtAuthRequest.getLogin());
        if(user.isPresent() && passwordEncoder.matches(jwtAuthRequest.getPassword(), user.get().getPassword())){
            return jwtProvider.createJwtToken(jwtAuthRequest.getLogin());
        }
        return "";
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean registration(RegistrationUser registrationUser){
        try {
            User user = new User();
            user.setName(registrationUser.getName());
            user.setLogin(registrationUser.getLogin());
            user.setEmail(registrationUser.getEmail());
            user.setRole(registrationUser.getRole());
            user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));

            User savedUser = userRepository.save(user);

            if (savedUser!=null) {
                return true;
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return false;
    }
}
