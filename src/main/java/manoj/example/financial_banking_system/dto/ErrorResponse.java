package manoj.example.financial_banking_system.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;

    // Default Constructor
    public ErrorResponse() {
    }

    // Parameterized Constructor
    public ErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setter
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}