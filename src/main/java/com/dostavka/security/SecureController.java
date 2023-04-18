package com.dostavka.security;

import com.dostavka.domain.request.JwtAuthRequest;
import com.dostavka.domain.request.RegistrationUser;
import com.dostavka.domain.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.net.http.HttpResponse;

@RestController
public class SecureController {
    private final SecurityService securityService;

    @Autowired
    public SecureController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpResponse> registrationUser(@RequestBody RegistrationUser registrationUser){
        return new ResponseEntity<>(securityService.registration(registrationUser)? HttpStatus.CREATED : HttpStatus.CONFLICT);

    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody JwtAuthRequest jwtAuthRequest){
        String result = securityService.getToken(jwtAuthRequest);
        if(!result.isBlank()){
            return new ResponseEntity<>(new JwtResponse(result), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
