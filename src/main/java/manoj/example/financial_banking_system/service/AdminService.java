package manoj.example.financial_banking_system.service;

import manoj.example.financial_banking_system.dto.AdminLoginRequest;
import manoj.example.financial_banking_system.dto.AdminLoginResponse;
import manoj.example.financial_banking_system.dto.RegisterRequest;
import manoj.example.financial_banking_system.entity.Account;
import manoj.example.financial_banking_system.entity.User;
import manoj.example.financial_banking_system.repository.AccountRepository;
import manoj.example.financial_banking_system.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import manoj.example.financial_banking_system.dto.UserResponse;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AdminService(UserRepository userRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // ==========================
    // Admin Login
    // ==========================

    public AdminLoginResponse login(AdminLoginRequest request) {

        User admin = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("Access Denied! Not an Admin.");
        }

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(admin.getEmail());

        return new AdminLoginResponse(
                "Admin Login Successful",
                token);
    }

    // ==========================
    // Admin Register
    // ==========================

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }

        User admin = new User();

        admin.setFullName(request.getFullName());
        admin.setEmail(request.getEmail());
        admin.setPhone(request.getPhone());

        admin.setPassword(
                passwordEncoder.encode(request.getPassword()));

        admin.setRole("ADMIN");

        userRepository.save(admin);

        return "Admin Registered Successfully";
    }

    // ==========================
    // Total Users
    // ==========================

    public long getTotalUsers() {

        return userRepository.count();

    }

    // ==========================
    // Total Accounts
    // ==========================

    public long getTotalAccounts() {

        return accountRepository.count();

    }

    // ==========================
    // Total Balance
    // ==========================

    public double getTotalBalance() {

        return accountRepository.findAll()
                .stream()
                .mapToDouble(Account::getBalance)
                .sum();

    }

    // ==========================
    // Get All Users
    // ==========================

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()

                .stream()

                .map(user -> new UserResponse(

                        user.getId(),

                        user.getFullName(),

                        user.getEmail(),

                        user.getPhone(),

                        user.getRole(),

                        user.getAccount() != null
                                ? user.getAccount().getBalance()
                                : 0.0

                ))

                .collect(Collectors.toList());

    }

}