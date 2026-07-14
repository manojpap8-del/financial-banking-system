package manoj.example.financial_banking_system.dto;

import manoj.example.financial_banking_system.enums.AccountType;

public class CreateAccountRequest {

    private AccountType accountType;

    public CreateAccountRequest() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}