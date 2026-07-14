package manoj.example.financial_banking_system.dto;

public class WithdrawRequest {

    private Double amount;

    public WithdrawRequest() {
    }

    public WithdrawRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}