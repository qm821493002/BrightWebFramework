package cn.bright.webframework.carrier;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2014/7/30.
 */

public class ActionOriginal {

    private String target ;
    private String motion ;
    private Map<String, String[]> params = new HashMap<String,String[]>();


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public void setParams(Map<String, String[]> params) {
        this.params = params;
    }
}
