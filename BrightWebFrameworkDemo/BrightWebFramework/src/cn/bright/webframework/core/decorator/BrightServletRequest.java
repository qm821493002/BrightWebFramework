package cn.bright.webframework.core.decorator;

import cn.bright.webframework.core.BrightContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by hp on 2014/8/1.
 */
public class BrightServletRequest extends HttpServletRequestWrapper{
    public BrightServletRequest(HttpServletRequest request) {
        super(request);
    }

    /**
     * 装饰getAttribute方法, 先从自己的上下文查找是否有符合数据
     * @param name
     * @return
     */
    @Override
    public Object getAttribute(String name) {

        BrightContext context = BrightContext.getInstance();
        Object value = context.get(name);
        if (value != null) {
            return value;
        }

        return super.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object o) {
        super.setAttribute(name, o);
    }
}
