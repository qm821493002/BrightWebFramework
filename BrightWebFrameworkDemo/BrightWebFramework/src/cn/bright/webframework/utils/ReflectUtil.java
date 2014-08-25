package cn.bright.webframework.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hp on 2014/8/6.
 */
public class ReflectUtil {
    public static Method findMethod(Class clazz, String methodName, Class... params) {

        Method method = null;
        try {
            method = clazz.getMethod(methodName, params);
        } catch (NoSuchMethodException e) {

            if (method == null) {

                Method[] methods = clazz.getMethods();
                for (Method m : methods) {
                        if(m.getName().equals(methodName)){

                            boolean flag=true;
                            Class<?>[] parameterTypes = m.getParameterTypes();
                            if (parameterTypes.length != params.length) {
                                continue;
                            }

                            for (int i =0;i<parameterTypes.length;i++){
                                            if(!equalsType(parameterTypes[i],params[i])){
                                                flag =false;
                                            }

                            }
                            if (flag) {
                                return m;
                            }

                        }
                }

            }
        }

        return method;
    }


    private static boolean equalsType(Class parameterType, Class param) {
        if (parameterType == Integer.class || parameterType == int.class) {
            if (param == Integer.class || param == int.class){
                return true;
            }
        }
        if (parameterType == Long.class || parameterType == long.class) {
            if (param == Long.class || param == long.class){
                return true;
            }
        }
        if (parameterType == Short.class || parameterType == short.class) {
            if (param == Short.class || param == short.class){
                return true;
            }
        }
        if (parameterType == Byte.class || parameterType == byte.class) {
            if (param == Byte.class || param == byte.class){
                return true;
            }
        }
        if (parameterType == Character.class || parameterType == char.class) {
            if (param == Character.class || param == char.class){
                return true;
            }
        }

        if (parameterType == Float.class || parameterType == float.class) {
            if (param == Float.class || param == float.class){
                return true;
            }
        }

        if (parameterType == Double.class || parameterType == double.class) {
            if (param == Double.class || param == double.class){
                return true;
            }
        }




        return false;
    }

}
