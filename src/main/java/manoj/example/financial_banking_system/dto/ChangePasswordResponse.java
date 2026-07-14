package manoj.example.financial_banking_system.dto;

public class ChangePasswordResponse {

    private String message;

    public ChangePasswordResponse() {
    }

    public ChangePasswordResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChangePasswordResponse changePassword(
        ChangePasswordRequest request,
        String token) {

    return new ChangePasswordResponse("Temporary");
}
}