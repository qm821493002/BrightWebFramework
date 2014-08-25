package cn.bright.webframework.core.decorator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2014/8/6.
 */
public class CGLIbRequestWrap {

    private final HttpServletRequest request;

    public CGLIbRequestWrap(HttpServletRequest request) {
        this.request = request;

    }

    public Object getAttribute(String key) {
        Object obj = request.getAttribute(key.trim());

        return obj;
    }




}
