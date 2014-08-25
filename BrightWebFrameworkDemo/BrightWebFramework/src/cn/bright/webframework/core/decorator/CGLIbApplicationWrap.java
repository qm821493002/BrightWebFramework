package cn.bright.webframework.core.decorator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2014/8/6.
 */
public class CGLIbApplicationWrap {

    private final ServletContext context;

    public CGLIbApplicationWrap(ServletContext context) {
        this.context = context;

    }

    public Object getAttribute(String key) {

        Object obj = context.getAttribute(key.trim());

        return obj;
    }




}
