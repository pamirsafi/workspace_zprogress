package com.zprogress.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtTokenEntityModel> authenticate(HttpServletRequest request) {
        var username = request.getHeader("username");
        var password = request.getHeader("password");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        var jwtToken = jwtTokenService.getToken(username);
        logger.info("got jwt ({}) ", jwtToken);
        return new ResponseEntity(new JwtTokenEntityModel(jwtToken), HttpStatus.OK);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
