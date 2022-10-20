package com.example.server.controllers;

import com.example.server.dto.JWTAuthResponse;
import com.example.server.dto.LoginDto;
import com.example.server.dto.RegistrationDto;
import com.example.server.entity.Role;
import com.example.server.entity.User;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import com.example.server.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/role")
    public ResponseEntity<?> getRole(@RequestBody LoginDto body){
        User user = userRepository.findByLogin(body.getLogin());
        return ResponseEntity.ok(user.getRoles());
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','OWNER')")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto body){

        if(userRepository.existsByLogin(body.getLogin())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if(Objects.equals(body.getRole(), "OWNER")){
            return new ResponseEntity<>("Invalid access level", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setLogin(body.getLogin());
        user.setPassword(passwordEncoder.encode(body.getPassword()));

        Role roles = roleRepository.findByAccessRight(body.getRole()).get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
