package cn.bright.webframework.helper.tags;

import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.enums.Scope;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by hp on 2014/8/6.
 */
public class BeanTag extends SimpleTagSupport {
    public static final String INSTACE_NAME = "BaseTag_INSTACE_NAME";

    private String className;
    private String scope;
    private String name;
    private StringWriter stringWriter = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        BrightContext brightContext = BrightContext.getInstance();
        Class clazz = null;
        try {
            clazz = Class.forName(className);
            Object instance = clazz.newInstance();
            brightContext.put(INSTACE_NAME, instance);
            if (scope == null) {
                brightContext.put(name, instance);
            } else {

                if ("request".equals(scope)) {
                    brightContext.put(Scope.REQUEST, name, instance);
                } else if ("session".equals(scope)) {
                    brightContext.put(Scope.SESSION, name, instance);
                } else if ("application".equals(scope)) {
                    brightContext.put(Scope.APPLICATION, name, instance);
                } else if ("bright".equals(scope)) {
                    brightContext.put(name, instance);
                }
            }
            getJspBody().invoke(stringWriter);
            getJspContext().getOut().println(stringWriter);

            brightContext.put(INSTACE_NAME, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
