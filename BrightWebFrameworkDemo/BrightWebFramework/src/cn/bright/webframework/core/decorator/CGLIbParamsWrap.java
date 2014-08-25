package cn.bright.webframework.core.decorator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2014/8/6.
 */
public class CGLIbParamsWrap {

    private final HttpServletRequest request;

    public CGLIbParamsWrap(HttpServletRequest request) {
        this.request = request;

    }

    public Object getAttribute(String key) {

        Object obj = request.getParameter(key.trim());

        return obj;

    }




}
