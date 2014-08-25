package cn.bright.webframework.exceptions;

/**
 * Created by hp on 2014/7/31.
 */
public class ValidationException extends  RuntimeException {

    public ValidationException(Throwable cause, String source, Class target) {
        super(cause);
        ;


    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
