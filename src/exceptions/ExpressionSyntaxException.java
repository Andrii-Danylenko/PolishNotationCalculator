package exceptions;

public class ExpressionSyntaxException extends RuntimeException {
    public ExpressionSyntaxException() {
    }

    public ExpressionSyntaxException(String message) {
        super(message);
    }

    public ExpressionSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionSyntaxException(Throwable cause) {
        super(cause);
    }

    public ExpressionSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
