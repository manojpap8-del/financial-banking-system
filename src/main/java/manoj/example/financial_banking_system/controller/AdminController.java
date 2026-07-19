package manoj.example.financial_banking_system.controller;

import manoj.example.financial_banking_system.dto.AdminLoginRequest;
import manoj.example.financial_banking_system.dto.AdminLoginResponse;
import manoj.example.financial_banking_system.dto.RegisterRequest;
import manoj.example.financial_banking_system.service.AdminService;
import java.util.List;
import manoj.example.financial_banking_system.dto.UserResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ==========================
    // Admin Register
    // ==========================

    @PostMapping("/register")
    public String registerAdmin(
            @RequestBody RegisterRequest request) {

        return adminService.register(request);
    }

    // ==========================
    // Admin Login
    // ==========================

    @PostMapping("/login")
    public AdminLoginResponse adminLogin(
            @RequestBody AdminLoginRequest request) {

        return adminService.login(request);
    }

    // ==========================
    // Get All Users
    // ==========================

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {

        return adminService.getAllUsers();

    }

    // ==========================
    // Total Users
    // ==========================

    @GetMapping("/total-users")
    public long getTotalUsers() {

        return adminService.getTotalUsers();

    }

    // ==========================
    // Total Accounts
    // ==========================

    @GetMapping("/total-accounts")
    public long getTotalAccounts() {

        return adminService.getTotalAccounts();

    }

    // ==========================
    // Total Balance
    // ==========================

    @GetMapping("/total-balance")
    public double getTotalBalance() {

        return adminService.getTotalBalance();

    }

}