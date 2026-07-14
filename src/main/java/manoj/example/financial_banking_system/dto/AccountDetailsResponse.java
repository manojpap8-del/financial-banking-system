package manoj.example.financial_banking_system.dto;

import java.time.LocalDate;
import manoj.example.financial_banking_system.enums.AccountStatus;

public class AccountDetailsResponse {

    private String fullName;
    private String email;
    private String phone;

    private String accountNumber;
    private String accountType;

    private Double balance;

    private AccountStatus status;

    private LocalDate openDate;

    public AccountDetailsResponse() {
    }

    public AccountDetailsResponse(String fullName,
                                  String email,
                                  String phone,
                                  String accountNumber,
                                  String accountType,
                                  Double balance,
                                  AccountStatus status,
                                  LocalDate openDate) {

        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.openDate = openDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }
}