package manoj.example.financial_banking_system.dto;

public class TransferResponse {

    private String message;
    private Double senderBalance;

    public TransferResponse() {
    }

    public TransferResponse(String message, Double senderBalance) {
        this.message = message;
        this.senderBalance = senderBalance;
    }

    public String getMessage() {
        return message;
    }

    public Double getSenderBalance() {
        return senderBalance;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderBalance(Double senderBalance) {
        this.senderBalance = senderBalance;
    }
}