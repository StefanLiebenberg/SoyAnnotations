package slieb.soy.exceptions;


public class NeedsDynamicConverterException extends RuntimeException {
    public NeedsDynamicConverterException() {
    }

    public NeedsDynamicConverterException(String message) {
        super(message);
    }

    public NeedsDynamicConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedsDynamicConverterException(Throwable cause) {
        super(cause);
    }

    public NeedsDynamicConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
