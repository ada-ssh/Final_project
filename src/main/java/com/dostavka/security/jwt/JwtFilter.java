package com.dostavka.security.jwt;

import com.dostavka.domain.User;
import com.dostavka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends GenericFilterBean{
    public String token;

    public String userLogin;

    private final JwtProvider jwtProvider;

    private final UserService userService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        token = ((HttpServletRequest)servletRequest).getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            token = token.substring(7);
            if(jwtProvider.isValid(token)){
                userLogin = jwtProvider.getLoginFromJwt(token);
                Optional<User> user = userService.getUserByLogin(userLogin);
                if(user.isPresent()){
                    UserDetails userDetails = org.springframework.security.core.userdetails.User
                            .withUsername(user.get().getLogin())
                            .password(user.get().getPassword())
                            .roles(userService.getRole(user.get().getId()))
                            .build();
                    UsernamePasswordAuthenticationToken userAuth =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(userAuth);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
