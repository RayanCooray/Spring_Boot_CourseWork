package lk.ijse.gdse.aad.spring_boot_coursework.exception;

public class MultipleUsersFoundException extends RuntimeException {
    public MultipleUsersFoundException(String message) {
        super(message);
    }
}