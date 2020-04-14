package com.niranjan.helpproject.Security.Controller;

import com.niranjan.helpproject.Security.Exception.AppException;
import com.niranjan.helpproject.Security.Model.*;
import com.niranjan.helpproject.Security.payloads.ApiResponse;
import com.niranjan.helpproject.Security.payloads.JwtAuthenticationResponse;
import com.niranjan.helpproject.Security.security.JwtTokenProvider;
import com.niranjan.helpproject.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody HashMap loginRequest) {
        System.out.println(loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.get("usernameOrEmail"),
                        loginRequest.get("password")
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String username = loginRequest.get("usernameOrEmail").toString();
        System.out.println(username);
        String role ="";

        String jwt = tokenProvider.generateToken(authentication);

        System.out.println(jwt);
        System.out.println(new JwtAuthenticationResponse(jwt).toString());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody HashMap<String,Object> signUpRequest) {
        System.out.println(signUpRequest.toString());
//        // Creating user's account
        User user = new User(signUpRequest.get("firstName").toString(), signUpRequest.get("rId").toString(),
                signUpRequest.get("email").toString(), signUpRequest.get("psw").toString());
      //  System.out.println(user.toString());
     // List<Map<String,String>> subjMap= (List<Map<String, String>>) signUpRequest.get("subject");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

           User result=null;
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("Customer Role not set."));
            Set<Role> roleSet=new HashSet<>();
            roleSet.add(userRole);
            user.setRoles(roleSet);
            System.out.println("hello");
             result = userRepository.save(user);
            System.out.println("user is saved ");

        Customer customer = new Customer(signUpRequest.get("rId").toString(),
                signUpRequest.get("firstName").toString(),
                signUpRequest.get("surname").toString(),
                signUpRequest.get("phoneNumber").toString(),
                signUpRequest.get("postalCode").toString(),
                signUpRequest.get("email").toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
//
   return ResponseEntity.created(location).body(new ApiResponse(true, "Customer registered successfully"));
  //   return ResponseEntity.ok();
    }
}