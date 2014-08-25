package cn.bright.webframework.carrier;

import java.util.List;

/**
 * Created by hp on 2014/8/4.
 */
public class Config {


    private List<String> modules;
    private String scanPackage;
    private String[] allclears;
    private boolean debug;
    private ErrorConfig errorConfig;

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setAllclears(String[] allclears) {
        this.allclears = allclears;
    }

    public String[] getAllclears() {
        return allclears;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setErrorConfig(ErrorConfig errorConfig) {
        this.errorConfig = errorConfig;
    }

    public ErrorConfig getErrorConfig() {
        return errorConfig;
    }
}
