package manoj.example.financial_banking_system.dto;

public class DepositResponse {

    private String message;
    private Double currentBalance;

    public DepositResponse() {
    }

    public DepositResponse(String message, Double currentBalance) {
        this.message = message;
        this.currentBalance = currentBalance;
    }

    public String getMessage() {
        return message;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
}