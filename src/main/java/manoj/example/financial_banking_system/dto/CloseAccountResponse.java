package manoj.example.financial_banking_system.dto;

public class CloseAccountResponse {

    private String message;
    private String accountStatus;

    public CloseAccountResponse() {
    }

    public CloseAccountResponse(String message, String accountStatus) {
        this.message = message;
        this.accountStatus = accountStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}