package cn.bright.webframework.exceptions;

/**
 * Created by hp on 2014/7/31.
 */
public class ModuleClassNoteFoundException extends  RuntimeException {

    public ModuleClassNoteFoundException(Throwable cause, String source, Class target) {
        super(cause);
        ;


    }

    public ModuleClassNoteFoundException(String message) {
        super(message);
    }

    public ModuleClassNoteFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
