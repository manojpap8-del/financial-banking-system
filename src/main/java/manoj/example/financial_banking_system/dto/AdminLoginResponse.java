package manoj.example.financial_banking_system.dto;

public class AdminLoginResponse {

    private String message;
    private String token;

    public AdminLoginResponse() {
    }

    public AdminLoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}