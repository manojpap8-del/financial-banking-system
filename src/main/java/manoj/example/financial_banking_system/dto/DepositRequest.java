package manoj.example.financial_banking_system.dto;

public class DepositRequest {

    private Double amount;

    public DepositRequest() {
    }

    public DepositRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}