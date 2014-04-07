package slieb.soy.exceptions;


public class UnsupportedFunctionality extends RuntimeException {
    public UnsupportedFunctionality() {
    }

    public UnsupportedFunctionality(String message) {
        super(message);
    }

    public UnsupportedFunctionality(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFunctionality(Throwable cause) {
        super(cause);
    }

    public UnsupportedFunctionality(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
