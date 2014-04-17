package slieb.soy.exceptions;


public class UnexpectedResultType extends RuntimeException {

    public UnexpectedResultType() {
    }

    public UnexpectedResultType(String message) {
        super(message);
    }

    public UnexpectedResultType(Class<?> expectedClass, Object result) {
        super(String.format("Expected %s, but got %s instead", expectedClass.getCanonicalName(), String.valueOf(result)));
    }
}
