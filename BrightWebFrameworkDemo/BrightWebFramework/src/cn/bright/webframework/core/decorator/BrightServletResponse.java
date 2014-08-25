package cn.bright.webframework.core.decorator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by hp on 2014/8/1.
 */
public class BrightServletResponse extends HttpServletResponseWrapper {
    public BrightServletResponse(HttpServletResponse response) {
        super(response);
    }


}
