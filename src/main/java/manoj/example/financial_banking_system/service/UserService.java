package manoj.example.financial_banking_system.service;

import manoj.example.financial_banking_system.dto.ChangePasswordRequest;
import manoj.example.financial_banking_system.dto.ChangePasswordResponse;
import manoj.example.financial_banking_system.dto.LoginRequest;
import manoj.example.financial_banking_system.dto.RegisterRequest;
import manoj.example.financial_banking_system.entity.User;
import manoj.example.financial_banking_system.exception.DuplicateEmailException;
import manoj.example.financial_banking_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import manoj.example.financial_banking_system.entity.Account;
import manoj.example.financial_banking_system.enums.AccountStatus;
import manoj.example.financial_banking_system.enums.AccountType;
import manoj.example.financial_banking_system.repository.AccountRepository;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Register User
    public void registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // Password Encrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhone(request.getPhone());
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        // Create Bank Account

        Account account = new Account();

        account.setAccountNumber("KX" + System.currentTimeMillis());

        account.setAccountType(AccountType.SAVINGS);

        account.setBalance(0.0);

        account.setStatus(AccountStatus.ACTIVE);

        account.setOpenDate(LocalDate.now());

        account.setUser(savedUser);

        // Save Account

        accountRepository.save(account);
    }

    // Login User
    public String loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Compare Encrypted Password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());
    }

    // change password

    public ChangePasswordResponse changePassword(ChangePasswordRequest request, String token) {

        // Remove "Bearer " from token if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract email from JWT
        String email = jwtService.extractEmail(token);

        // Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Verify old password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old Password is Incorrect");
        }

        // Encrypt and save new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return new ChangePasswordResponse("Password Changed Successfully");
    }
}