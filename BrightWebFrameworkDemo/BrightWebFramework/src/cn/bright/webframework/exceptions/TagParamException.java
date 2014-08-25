package cn.bright.webframework.exceptions;

/**
 * Created by hp on 2014/8/6.
 */
public class TagParamException  extends  RuntimeException{
    public TagParamException() {
    }

    public TagParamException(String message) {
        super(message);
    }

    public TagParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagParamException(Throwable cause) {
        super(cause);
    }

    public TagParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
