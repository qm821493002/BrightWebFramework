package cn.bright.webframework.carrier;

import cn.bright.webframework.annotations.Actor;
import cn.bright.webframework.core.BrightContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2014/7/30.
 */
public class ActionCarrier {

    private BrightContext context;
    private int step;
    private Object actor;
    private Method motion ;
    private Object[] params ;

    private ActionOriginal actionOriginal = new ActionOriginal();
    private FileDomain fileDomain;

    //用于存放类型转换错误或者验证错误
    private Map<String, String> warning = new HashMap<String, String>();

    public void addWarning(String key, String message) {
        if (!warning.containsKey(key)) {

            warning.put(key, message);
        }


    }

    public Map<String, String> getWarning() {
        return warning;
    }

    public void setWarning(Map<String, String> warning) {
        this.warning = warning;
    }

    public void setContext(BrightContext context) {
        this.context = context;
    }

    public BrightContext getContext() {
        return context;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Object getActor() {
        return actor;
    }

    public void setActor(Object actor) {
        this.actor = actor;
    }

    public ActionOriginal getActionOriginal() {
        return actionOriginal;
    }

    public void setActionOriginal(ActionOriginal actionOriginal) {
        this.actionOriginal = actionOriginal;
    }

    public Method getMotion() {
        return motion;
    }

    public void setMotion(Method motion) {
        this.motion = motion;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setFileDomain(FileDomain fileDomain) {
        this.fileDomain = fileDomain;
    }

    public FileDomain getFileDomain() {
        return fileDomain;
    }
}
