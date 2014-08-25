package cn.bright.webframework.helper;

import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.enums.Scope;
import cn.bright.webframework.results.ComplexResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by hp on 2014/8/5.
 */
public class ActorGuide {
    private BrightContext brightContext;

    public String forward(String path) {

        return "forward:" + path;
    }

    public ComplexResult forward(String path,String key ,Object value) {


        return new ComplexResult("forward:" + path,key,value);
    }


    public String redirect(String path) {

        return "redirect:" + path;
    }

    public void put(String key, String value) {
        brightContext.put(key, value);
    }

    public void put(Scope scope, String key, String value) {
        switch (scope) {
            case BRIGHT:
                this.put(key, value);
                break;

            case REQUEST:
                getRequest().setAttribute(key, value);
                break;
            case SESSION:
                getRequest().getSession().setAttribute(key, value);
                break;
            case APPLICATION:
                getServletContext().setAttribute(key, value);
                break;
        }

    }

    public ServletContext getServletContext() {

        return brightContext.getRequest().getServletContext();
    }

    public HttpServletRequest getRequest() {

        return brightContext.getRequest();
    }

    public HttpServletResponse getResponse() {

        return brightContext.getResponse();
    }

    public void copyFile(File file, String path) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(new File(path));
        int len = 0;
        byte[] buffer = new byte[1024];

        while ((len = inputStream.read(buffer)) > 0) {

            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        ;
        outputStream.close();
        ;

    }


    public BrightContext getBrightContext() {
        return brightContext;
    }

    public void setBrightContext(BrightContext brightContext) {
        this.brightContext = brightContext;
    }
}
