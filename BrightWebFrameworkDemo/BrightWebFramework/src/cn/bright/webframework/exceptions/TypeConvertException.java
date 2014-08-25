package cn.bright.webframework.exceptions;

/**
 * Created by hp on 2014/7/31.
 */
public class TypeConvertException extends  RuntimeException {

    public TypeConvertException(Throwable cause,String source,Class target) {
        super(cause);
        ;


    }

    public TypeConvertException(String message) {
        super(message);
    }

    public TypeConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
