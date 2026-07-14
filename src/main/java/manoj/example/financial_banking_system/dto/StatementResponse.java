package manoj.example.financial_banking_system.dto;

import java.time.LocalDateTime;

import manoj.example.financial_banking_system.enums.TransactionType;

public class StatementResponse {

    private String transactionId;
    private TransactionType transactionType;
    private Double amount;
    private LocalDateTime transactionDate;

    public StatementResponse() {
    }

    public StatementResponse(String transactionId,
                             TransactionType transactionType,
                             Double amount,
                             LocalDateTime transactionDate) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}