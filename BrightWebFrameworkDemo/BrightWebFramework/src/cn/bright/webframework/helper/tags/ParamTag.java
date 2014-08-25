package cn.bright.webframework.helper.tags;

import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.exceptions.TagParamException;
import cn.bright.webframework.utils.ReflectUtil;
import cn.bright.webframework.utils.TextUtils;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by hp on 2014/8/6.
 */
public class ParamTag extends BaseTag {
    private String value  ;

    @Override
    public void doTag() throws JspException, IOException {
        Object instance = brightContext.get(BeanTag.INSTACE_NAME);
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(value)){
            String methodName="set"+name.substring(0,1).toUpperCase()+name.substring(1);
            try {
                Method method = ReflectUtil.findMethod(instance.getClass(),methodName,new Class[]{engine.eval(value).getClass()});

                method.invoke(instance, engine.eval(value));

            } catch (Exception e) {
                e.printStackTrace();
                throw new TagParamException( "输入的参数异常!(字符串请用单引号包裹)",e);
            }

        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
