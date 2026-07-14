package manoj.example.financial_banking_system.service;

import manoj.example.financial_banking_system.controller.AccountController;
import manoj.example.financial_banking_system.dto.CreateAccountRequest;
import manoj.example.financial_banking_system.dto.CreateAccountResponse;
import manoj.example.financial_banking_system.dto.DepositRequest;
import manoj.example.financial_banking_system.dto.DepositResponse;
import manoj.example.financial_banking_system.dto.TransferRequest;
import manoj.example.financial_banking_system.dto.TransferResponse;
import manoj.example.financial_banking_system.dto.WithdrawRequest;
import manoj.example.financial_banking_system.dto.WithdrawResponse;
import manoj.example.financial_banking_system.entity.Account;
import manoj.example.financial_banking_system.entity.User;
import manoj.example.financial_banking_system.enums.AccountStatus;
import manoj.example.financial_banking_system.enums.TransactionType;
import manoj.example.financial_banking_system.repository.AccountRepository;
import manoj.example.financial_banking_system.repository.TransactionRepository;
import manoj.example.financial_banking_system.repository.UserRepository;
import manoj.example.financial_banking_system.dto.BalanceResponse;


import java.time.LocalDateTime;

import manoj.example.financial_banking_system.entity.Transaction;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.Optional;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import manoj.example.financial_banking_system.dto.StatementResponse;
import manoj.example.financial_banking_system.entity.Transaction;
import manoj.example.financial_banking_system.dto.AccountDetailsResponse;
import manoj.example.financial_banking_system.dto.CloseAccountResponse;

@Service
public class AccountService {

        private final AccountRepository accountRepository;
        private final UserRepository userRepository;
        private final TransactionRepository transactionRepository;

        public AccountService(AccountRepository accountRepository,
                        UserRepository userRepository,
                        TransactionRepository transactionRepository) {

                this.accountRepository = accountRepository;
                this.userRepository = userRepository;
                this.transactionRepository = transactionRepository;
        }

        public CreateAccountResponse createAccount(CreateAccountRequest request) {

                // 1. Get Logged-in User
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String email = authentication.getName();

                // 2. Find User
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // 3. Check Existing Account
                if (accountRepository.existsByUser(user)) {
                        throw new RuntimeException("Account already exists");
                }

                // 4. Create Account Object
                Account account = new Account();

                account.setAccountNumber(generateAccountNumber());

                account.setAccountType(request.getAccountType());

                account.setBalance(0.0);

                account.setStatus(AccountStatus.ACTIVE);

                account.setOpenDate(LocalDate.now());

                account.setUser(user);

                // 5. Save Account
                accountRepository.save(account);

                // 6. Return Response
                return new CreateAccountResponse(
                                "Account Created Successfully",
                                account.getAccountNumber());
        }

        // Generate Random 10-digit Account Number
        private String generateAccountNumber() {

                Random random = new Random();

                long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);

                return String.valueOf(number);
        }

        // _----- method to perfrom deposit
        // operation---------------------------------------
        public DepositResponse depositMoney(DepositRequest request) {

                // 1. Get Logged-in User
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String email = authentication.getName();

                // 2. Find User
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // 3. Find Account
                Account account = accountRepository.findByUser(user)
                                .orElseThrow(() -> new RuntimeException("Account not found"));

                // 4. Validate Amount
                if (request.getAmount() <= 0) {
                        throw new RuntimeException("Invalid Amount");
                }

                // 5. Update Balance
                account.setBalance(
                                account.getBalance() + request.getAmount());

                // 6. Save Updated Account
                accountRepository.save(account);

                // 7. Save Transaction History
                saveTransaction(account, TransactionType.DEPOSIT, request.getAmount());

                // 8. Return Response
                return new DepositResponse(
                                "Amount Deposited Successfully",
                                account.getBalance());
        }

        public WithdrawResponse withdrawMoney(WithdrawRequest request) {

                // 1. Get Logged-in User
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String email = authentication.getName();

                // 2. Find User
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // 3. Find Account
                Account account = accountRepository.findByUser(user)
                                .orElseThrow(() -> new RuntimeException("Account not found"));

                // 4. Validate Amount
                if (request.getAmount() <= 0) {
                        throw new RuntimeException("Invalid Amount");
                }

                // 5. Check Balance
                if (account.getBalance() < request.getAmount()) {
                        throw new RuntimeException("Insufficient Balance");
                }

                // 6. Withdraw Money
                account.setBalance(
                                account.getBalance() - request.getAmount());

                // 7. Save Updated Account
                accountRepository.save(account);
                // 8.save transction history
                saveTransaction(
                                account,
                                TransactionType.WITHDRAW,
                                request.getAmount());

                // 8. Return Response
                return new WithdrawResponse(
                                "Amount Withdrawn Successfully",
                                account.getBalance());
        }

        // ----------------Transfer money
        // method--------------------------------------------------------------

        @Transactional
        public TransferResponse transferMoney(TransferRequest request) {

                // 1. Logged-in User
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                String email = authentication.getName();

                // 2. Sender User
                User senderUser = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // 3. Sender Account
                Account senderAccount = accountRepository.findByUser(senderUser)
                                .orElseThrow(() -> new RuntimeException("Sender account not found"));

                // 4. Receiver Account
                Account receiverAccount = accountRepository
                                .findByAccountNumber(request.getReceiverAccountNumber())
                                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

                // 5. Cannot transfer to own account
                if (senderAccount.getAccountNumber()
                                .equals(receiverAccount.getAccountNumber())) {

                        throw new RuntimeException(
                                        "Cannot transfer to your own account");
                }

                // 6. Amount Validation
                if (request.getAmount() <= 0) {
                        throw new RuntimeException("Invalid Amount");
                }

                // 7. Balance Validation
                if (senderAccount.getBalance() < request.getAmount()) {
                        throw new RuntimeException("Insufficient Balance");
                }

                // 8. Debit Sender
                senderAccount.setBalance(
                                senderAccount.getBalance() - request.getAmount());

                // 9. Credit Receiver
                receiverAccount.setBalance(
                                receiverAccount.getBalance() + request.getAmount());

                // 10. Save Both Accounts
                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);

                // 11. save transction history
                saveTransaction(
                                senderAccount,
                                TransactionType.TRANSFER,
                                request.getAmount());

                // 12. Response
                return new TransferResponse(
                                "Money Transferred Successfully",
                                senderAccount.getBalance());
        }

        // ------for mini statement--------------------------------

        private void saveTransaction(Account account,
                        TransactionType transactionType,
                        Double amount) {

                Transaction transaction = new Transaction();

                transaction.setTransactionId(generateTransactionId());

                transaction.setTransactionType(transactionType);

                transaction.setAmount(amount);

                transaction.setTransactionDate(LocalDateTime.now());

                transaction.setAccount(account);

                transactionRepository.save(transaction);
        }

        private String generateTransactionId() {

                return "TXN" + System.currentTimeMillis();

        }

   //------------------     transcation list------------------------------------

   public List<StatementResponse> getMiniStatement() {

    // 1. Logged-in User
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    // 2. Find User
    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    // 3. Find Account
    Account account = accountRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Account not found"));

    // 4. Get Transactions
    List<Transaction> transactions =
            transactionRepository.findByAccount(account);

    // 5. Convert Entity → DTO
    List<StatementResponse> response = new ArrayList<>();

    for (Transaction transaction : transactions) {

        response.add(
                new StatementResponse(
                        transaction.getTransactionId(),
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getTransactionDate()
                )
        );
    }

    return response;
}

// ---------------check balance method--------------------------------------

public BalanceResponse checkBalance() {

    // 1. Get Logged-in User
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    // 2. Find User
    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    // 3. Find Account
    Account account = accountRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Account not found"));

    // 4. Return Balance
    return new BalanceResponse(
            account.getAccountNumber(),
            account.getBalance()
    );
}

//----------Get account holder detail-------------------------------------------

public AccountDetailsResponse getAccountDetails() {

    // 1. Get Logged-in User
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    // 2. Find User
    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    // 3. Find Account
    Account account = accountRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Account not found"));

    // 4. Return Account Details
    return new AccountDetailsResponse(
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            account.getAccountNumber(),
            account.getAccountType().name(),
            account.getBalance(),
            account.getStatus(),
            account.getOpenDate()
    );
}

// --------------close account method---------------------------

public CloseAccountResponse closeAccount() {

    // 1. Get Logged-in User
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    // 2. Find User
    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    // 3. Find Account
    Account account = accountRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Account not found"));

    // 4. Check if already closed
    if (account.getStatus() == AccountStatus.CLOSED) {
        throw new RuntimeException("Account is already closed");
    }

    // 5. Close Account
    account.setStatus(AccountStatus.CLOSED);

    // 6. Save
    accountRepository.save(account);

    // 7. Return Response
    return new CloseAccountResponse(
            "Account Closed Successfully",
            account.getStatus().name()
    );
}


}