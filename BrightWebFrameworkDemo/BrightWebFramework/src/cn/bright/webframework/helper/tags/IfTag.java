package cn.bright.webframework.helper.tags;

import cn.bright.webframework.core.ExpressionEngine;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by hp on 2014/8/6.
 */
public class IfTag extends SimpleTagSupport {
    public static final  String  NEED_ELSE_FLAG="IfTag_NEED_ELSE_FLAG";

    private String expression;

    StringWriter stringWriter = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        ExpressionEngine engine = ExpressionEngine.getInstance();

        Object eval = engine.eval(expression);
        if (eval instanceof Boolean) {
            Boolean flag = (Boolean) eval;
            if (flag) {
                getJspBody().invoke(stringWriter);
                getJspContext().getOut().println(stringWriter);
            }else{
                //设置标志供ElseTag使用
                getJspContext().setAttribute(NEED_ELSE_FLAG, true);

            }
        }

    }


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
