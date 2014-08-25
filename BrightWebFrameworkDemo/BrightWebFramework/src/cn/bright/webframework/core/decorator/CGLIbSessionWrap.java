package cn.bright.webframework.core.decorator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hp on 2014/8/6.
 */
public class CGLIbSessionWrap {

    private final HttpSession session;

    public CGLIbSessionWrap(HttpSession session) {
        this.session = session;

    }

    public Object getAttribute(String key) {
        Object obj = session.getAttribute(key.trim());

        return obj;
    }




}
