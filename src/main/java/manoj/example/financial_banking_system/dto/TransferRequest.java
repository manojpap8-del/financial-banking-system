package manoj.example.financial_banking_system.dto;

public class TransferRequest {

    private String receiverAccountNumber;
    private Double amount;

    public TransferRequest() {
    }

    public TransferRequest(String receiverAccountNumber, Double amount) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}