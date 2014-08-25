package cn.bright.webframework.helper.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by hp on 2014/8/6.
 */
public class ElseTag extends SimpleTagSupport {

    StringWriter stringWriter = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        Object flag  =getJspContext().getAttribute(IfTag.NEED_ELSE_FLAG);
        if (flag!=null &&(Boolean)flag ==true){

            getJspBody().invoke(stringWriter);
            getJspContext().getOut().println(stringWriter);
            getJspContext().setAttribute(IfTag.NEED_ELSE_FLAG,null);
        }

    }
}
