package manoj.example.financial_banking_system.controller;

import manoj.example.financial_banking_system.dto.CreateAccountRequest;
import manoj.example.financial_banking_system.dto.CreateAccountResponse;
import manoj.example.financial_banking_system.dto.DepositRequest;
import manoj.example.financial_banking_system.dto.DepositResponse;
import manoj.example.financial_banking_system.dto.RegisterRequest;
import manoj.example.financial_banking_system.service.AccountService;
import manoj.example.financial_banking_system.dto.WithdrawRequest;
import manoj.example.financial_banking_system.dto.WithdrawResponse;
import manoj.example.financial_banking_system.dto.TransferRequest;
import manoj.example.financial_banking_system.dto.TransferResponse;
import java.util.List;
import manoj.example.financial_banking_system.dto.StatementResponse;
import manoj.example.financial_banking_system.dto.BalanceResponse;
import manoj.example.financial_banking_system.dto.AccountDetailsResponse;
import manoj.example.financial_banking_system.dto.CloseAccountResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public CreateAccountResponse createAccount(
            @RequestBody CreateAccountRequest request) {

        return accountService.createAccount(request);
    }
    // ------------deposit money controller-----------------

    @PostMapping("/deposit")
    public DepositResponse depositMoney(
            @RequestBody DepositRequest request) {

        return accountService.depositMoney(request);
    }

    // ------------withdrawl money
    // controller------------------------------------------------------------------

    @PostMapping("/withdraw")
    public WithdrawResponse withdrawMoney(
            @RequestBody WithdrawRequest request) {

        return accountService.withdrawMoney(request);
    }

    // ------------------transfer money
    // controller--------------------------------------------------

    @PostMapping("/transfer")
    public TransferResponse transferMoney(
            @RequestBody TransferRequest request) {

        return accountService.transferMoney(request);
    }

    // ---------------------for mini statement----------------------

    @GetMapping("/statement")
    public List<StatementResponse> getMiniStatement() {

        return accountService.getMiniStatement();
    }

    // ------------for check balance--------------------------------

    @GetMapping("/balance")
    public BalanceResponse checkBalance() {

        return accountService.checkBalance();
    }

    // ------------for account holder detail-------------------------------------

    @GetMapping("/details")
    public AccountDetailsResponse getAccountDetails() {

        return accountService.getAccountDetails();
    }

    // ----------------------for closing account------------------
    @PutMapping("/close")
    public CloseAccountResponse closeAccount() {

        return accountService.closeAccount();
    }

}