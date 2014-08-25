package cn.bright.webframework.helper.tags;

import cn.bright.webframework.core.ExpressionEngine;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by hp on 2014/8/6.
 */
public class OutTag extends SimpleTagSupport {
    private String expression;

    @Override
    public void doTag() throws JspException, IOException {
        ExpressionEngine engine = ExpressionEngine.getInstance();
        Object eval = engine.eval(expression);

        getJspContext().getOut().println(eval);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
