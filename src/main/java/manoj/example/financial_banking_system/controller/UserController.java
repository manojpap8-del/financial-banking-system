package manoj.example.financial_banking_system.controller;

import manoj.example.financial_banking_system.dto.RegisterRequest;
import manoj.example.financial_banking_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import manoj.example.financial_banking_system.dto.ChangePasswordRequest;
import manoj.example.financial_banking_system.dto.ChangePasswordResponse;
import manoj.example.financial_banking_system.dto.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

        userService.registerUser(request);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {

        return userService.loginUser(request);

    }

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {

        return "Welcome " + authentication.getName();
    }

    // change password

    @PutMapping("/change-password")
    public ChangePasswordResponse changePassword(

            @Valid @RequestBody ChangePasswordRequest request,

            @RequestHeader("Authorization") String token) {

        return userService.changePassword(request, token);
    }
}