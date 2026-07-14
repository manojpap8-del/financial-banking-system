package manoj.example.financial_banking_system.dto;

public class CreateAccountResponse {

    private String message;
    private String accountNumber;

    public CreateAccountResponse() {
    }

    public CreateAccountResponse(String message, String accountNumber) {
        this.message = message;
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}