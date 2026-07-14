package manoj.example.financial_banking_system.dto;

public class BalanceResponse {

    private String accountNumber;
    private Double balance;

    public BalanceResponse() {
    }

    public BalanceResponse(String accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}