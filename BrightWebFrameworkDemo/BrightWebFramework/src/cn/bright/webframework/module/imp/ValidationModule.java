package cn.bright.webframework.module.imp;

import cn.bright.webframework.annotations.Outfit;
import cn.bright.webframework.annotations.Validator;
import cn.bright.webframework.annotations.validations.Regex;
import cn.bright.webframework.annotations.validations.Required;
import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.ErrorConfig;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.enums.FailedBehavior;
import cn.bright.webframework.exceptions.ValidationException;
import cn.bright.webframework.module.Module;
import cn.bright.webframework.utils.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hp on 2014/7/31.
 */
public class ValidationModule implements Module {
    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler) throws Exception {
        Object[] params = carrier.getParams();
        if (params != null) { //如果等于空那么说明无参方法 不用验证


            for (Object param : params) {

                Validator validator = param.getClass().getAnnotation(Validator.class);
                if (validator != null) {//需要验证
                    validationParam(carrier,param);
                }

            }
        }
        handler.next();
    }

    private void validationParam(ActionCarrier carrier, Object param) throws Exception {
        Class<?> clazz = param.getClass();
        String error = null;
        String filedName = "";
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
                filedName= field.getName();

            //必填验证
            Required RequiredAnnotation = field.getAnnotation(Required.class);
            if (RequiredAnnotation != null) {

                if (!handleRequired(param, field)) {
                    error = param.getClass().getSimpleName() + "类中的字段:" + field.getName() + "验证失败\n" +
                            "(该属性值必填)";
                    break;
                }
            }

            //正则表达式验证


            Regex regexAnnotation = field.getAnnotation(Regex.class);
            if (regexAnnotation != null) {
                if (!handleRegex(param, field, regexAnnotation)) {
                    error = param.getClass().getSimpleName() + "类中的字段:" + field.getName() + "验证失败\n" +
                            "(不匹配正则表达式 \"" + regexAnnotation.value() + "\")";
                    break;
                }
            }

        }

        if (error != null) {
            ErrorConfig errorConfig = carrier.getContext().getConfig().getErrorConfig();

            if (errorConfig.getValidationFailedBehavior() == FailedBehavior.ADD_WARING_FIELD) {
                carrier.addWarning(filedName, error);
            }else{
                throw new ValidationException(error);
            }


        }

    }

    private boolean handleRegex(Object param, Field field, Regex regexAnnotation) throws Exception {
        String regex = regexAnnotation.value();
        Pattern pattern = Pattern.compile(regex);
        Object value = field.get(param);
        Matcher matcher = pattern.matcher(value.toString());


        return matcher.matches();
    }

    private boolean handleRequired(Object param, Field field) throws Exception {
        Object value = field.get(param);



        if (!TextUtils.isEmpty((String) value)) {


            return true;
        } else {
            return false;
        }


    }
}
