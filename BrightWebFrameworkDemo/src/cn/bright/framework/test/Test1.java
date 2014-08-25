package cn.bright.framework.test;

import cn.bright.webframework.annotations.Actor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hp on 2014/7/30.
 */
public class Test1 {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("t[aeio]n");
        Matcher tan = pattern.matcher("tcn");
        System.out.println(tan.matches());


    }

}
