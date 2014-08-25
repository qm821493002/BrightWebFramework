package cn.bright.webframework.helper.tags;

import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.core.ExpressionEngine;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.StringWriter;

/**
 * Created by hp on 2014/8/6.
 */
public class BaseTag extends SimpleTagSupport {


    protected ExpressionEngine engine = ExpressionEngine.getInstance();
    protected String expression;//反应集合的表达式
    protected String name ; //存放生成对象的名称
    protected StringWriter stringWriter = new StringWriter();//用于输出对象
    protected BrightContext brightContext = BrightContext.getInstance();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


}
