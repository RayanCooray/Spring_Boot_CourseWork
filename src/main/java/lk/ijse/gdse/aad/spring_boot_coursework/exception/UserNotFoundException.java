package lk.ijse.gdse.aad.spring_boot_coursework.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}