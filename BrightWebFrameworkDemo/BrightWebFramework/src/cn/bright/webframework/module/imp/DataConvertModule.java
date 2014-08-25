package cn.bright.webframework.module.imp;

import cn.bright.webframework.annotations.Outfit;
import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.ErrorConfig;
import cn.bright.webframework.carrier.FileDomain;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.enums.FailedBehavior;
import cn.bright.webframework.exceptions.TypeConvertException;
import cn.bright.webframework.module.Module;
import cn.bright.webframework.utils.TypeConvert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.Format;
import java.util.*;

/**
 * Created by hp on 2014/7/31.
 */
public class DataConvertModule implements Module {
    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler) throws Exception {


        Parameter[] parameters = carrier.getMotion().getParameters();

        List<Object> objectList = new ArrayList<Object>();
        //遍历方法签名中的参数 挨个处理
        for (int i = 0; i < parameters.length; i++) {

            try {
                Object o = handleParameter(carrier, parameters[i]);
                objectList.add(o);

            } catch (Exception e) {

                throw e;
            }


        }
        //讲处理结果加入carrier中

        if (objectList.size() > 0) {
            carrier.setParams(objectList.toArray());

        } else {

            carrier.setParams(null);
        }

        handler.next();
    }

    private Object handleParameter(ActionCarrier carrier, Parameter parameter) throws Exception {


        if (parameter.getAnnotation(Outfit.class) != null) {

            return parseParamToOutfit(carrier, parameter);
        }

        if (parameter.getType() == Map.class) {
            return parseParamToMap(carrier, parameter);
        }

        if (parameter.getType() == ActionCarrier.class) {
            return carrier;

        }

        if (parameter.getType() == BrightContext.class) {
            return carrier.getContext();

        }

        if (parameter.getType() == FileDomain.class) {
            return carrier.getFileDomain();

        }
        return null;

    }

    private Object parseParamToMap(ActionCarrier carrier, Parameter parameter) {
        Class<?> clazz = parameter.getType();
        Map<String, String[]> params = carrier.getActionOriginal().getParams();

        return params;

    }

    private Object parseParamToOutfit(ActionCarrier carrier, Parameter parameter) throws Exception {

        Class<?> clazz = parameter.getType();
        Object instance = clazz.newInstance();
        Map<String, String[]> params = carrier.getActionOriginal().getParams();

        Set<String> keys = params.keySet();
        for (String key : keys) {
            String[] values = params.get(key);
            if (values.length < 2) {//这里只处理的参数有单个值的情况


                Method method = null;
                Class<?> fieldType = null;
                try {

                    String setMethodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                    //反射私有字段获取set方法参数的类型
                    Field declaredField = clazz.getDeclaredField(key);
                    declaredField.setAccessible(true);
                    fieldType = declaredField.getType();
                    method = clazz.getMethod(setMethodName, fieldType);
                } catch (Exception e) {//如果这里报错那么说明没有 注入字段对应的方法 可以直接忽略
                    continue;
                }
                try {
                    if (method != null) {

                        //把字符串转换成对应类型然后填充到set方法中
                        Object convertedObject = TypeConvert.convertToObject(values[0], fieldType);
                        method.invoke(instance, convertedObject);

                    }
                } catch (Exception e) {//此阶段说明类型转换异常 不能转换为相应的类型 需要处理
                    ErrorConfig errorConfig = carrier.getContext().getConfig().getErrorConfig();
                    String message = "类型转换失败 参数:%s(参数名:%s)  到目标类:%s 的字段类型:%s";
                    message = String.format(message,Arrays.toString(values), key,  clazz.getName(),fieldType.getName());


                    if (errorConfig .getConvertfailedBehavior()== FailedBehavior.ADD_WARING_FIELD) {
                        carrier.addWarning(key,message);
                    }else{

                            throw new TypeConvertException(message,e);
                    }

                }

            } else {
                //todo: 需要处理一个参数有多个值的情况

            }
        }

        return instance;
    }
}
