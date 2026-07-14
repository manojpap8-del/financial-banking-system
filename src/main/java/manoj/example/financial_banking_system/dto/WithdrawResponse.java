package manoj.example.financial_banking_system.dto;

public class WithdrawResponse {

    private String message;
    private Double currentBalance;

    public WithdrawResponse() {
    }

    public WithdrawResponse(String message, Double currentBalance) {
        this.message = message;
        this.currentBalance = currentBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
}