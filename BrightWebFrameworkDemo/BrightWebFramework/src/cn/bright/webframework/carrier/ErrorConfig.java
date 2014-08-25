package cn.bright.webframework.carrier;

/**
 * Created by hp on 2014/8/7.
 */
public class ErrorConfig {

    private Enum  convertfailedBehavior ;
    private Enum  validationFailedBehavior ;

    public Enum getConvertfailedBehavior() {
        return convertfailedBehavior;
    }

    public void setConvertfailedBehavior(Enum convertfailedBehavior) {
        this.convertfailedBehavior = convertfailedBehavior;
    }

    public Enum getValidationFailedBehavior() {
        return validationFailedBehavior;
    }

    public void setValidationFailedBehavior(Enum validationFailedBehavior) {
        this.validationFailedBehavior = validationFailedBehavior;
    }
}
