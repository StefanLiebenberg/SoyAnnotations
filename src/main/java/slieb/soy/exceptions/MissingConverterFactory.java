package slieb.soy.exceptions;


import static java.lang.String.format;

public class MissingConverterFactory extends RuntimeException {

    public static final String MESSAGE_FORMAT = "Cannot find converter factory for %s";

    public MissingConverterFactory(Class<?> classObject) {
        this(format(MESSAGE_FORMAT, classObject.getCanonicalName()));
    }

    public MissingConverterFactory() {
    }

    public MissingConverterFactory(String message) {
        super(message);
    }

    public MissingConverterFactory(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingConverterFactory(Throwable cause) {
        super(cause);
    }

    public MissingConverterFactory(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
