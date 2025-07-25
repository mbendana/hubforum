package com.mhalton.hubforum.controller;

import com.mhalton.hubforum.domain.user.User;
import com.mhalton.hubforum.domain.user.rec.UserData;
import com.mhalton.hubforum.infra.security.TokenService;
import com.mhalton.hubforum.infra.security.rec.JWTTokenData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            TokenService tokenService
                         )
    {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> login(
            @RequestBody @Valid
            UserData userData)
    {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());

        Authentication authenticatedUser = authenticationManager.authenticate(authToken);

        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(jwtToken));
    }
}
