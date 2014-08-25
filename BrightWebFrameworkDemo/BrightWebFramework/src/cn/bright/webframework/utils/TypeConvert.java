package cn.bright.webframework.utils;

import cn.bright.webframework.exceptions.TypeConvertException;

/**
 * Created by hp on 2014/7/31.
 */
public class TypeConvert {

    /**
     * 将字符串转换为target指定类型,用于反射方法调用
     *
     * @param source 要转换的字符串
     * @param target 转换为什么类型
     * @return 转换后的对象
     */
    public static Object convertToObject(String source, Class target) {



            //todo:  现在还不支持把字符串转换为Domain对象

            if (target == String.class) {

                return source;
            }

            if (target == int.class) {

                return Integer.parseInt(source
                );
            }

            if (target == Integer.class) {

                return Integer.parseInt(source);
            }

            if (target == Float.class) {

                return Float.parseFloat(source);
            }

            if (target == float.class) {

                return Float.parseFloat(source);
            }


            if (target == double.class) {

                return Double.parseDouble(source);
            }


            if (target == Double.class) {

                return Double.parseDouble(source);
            }


            if (target == Long.class) {

                return Long.parseLong(source);
            }

            if (target == long.class) {

                return Long.parseLong(source);
            }


            if (target == boolean.class) {

                return Boolean.parseBoolean(source);
            }


            if (target == Boolean.class) {

                return Boolean.parseBoolean(source);
            }


            if (target == java.util.Date.class) {

                return new java.util.Date(source);
            }


            if (target == short.class) {

                return Short.parseShort(source);
            }


            if (target == Short.class) {

                return Short.parseShort(source);
            }


            if (target == Byte.class) {

                return Byte.parseByte(source);
            }

            if (target == byte.class) {

                return Byte.parseByte(source);
            }

          throw new TypeConvertException("不支持的转换类型");

    }


}
