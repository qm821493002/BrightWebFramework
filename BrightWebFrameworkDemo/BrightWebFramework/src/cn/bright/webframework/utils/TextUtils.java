package cn.bright.webframework.utils;

/**
 * Created by hp on 2014/7/31.
 */
public class TextUtils {

    public static boolean isEmpty(String text) {

        if (text == null) {

            return true;
        }

        if ("".equals(text.trim())) {

            return true;
        }

        return false;
    }
}
