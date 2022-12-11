package de.eclipse.mail.exception;

public class EmailException extends RuntimeException {


    public EmailException() {
        super();
    }

    public EmailException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmailException(final String message) {
        super(message);
    }

    public EmailException(final Throwable cause) {
        super(cause);
    }

}
