package cn.bright.framework.demo;

import cn.bright.webframework.utils.PackageUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by hp on 2014/7/30.
 */
public class UtilTestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        List<String> classNames = PackageUtil.getClassNames("cn.bright.framework.demo");
        for (String str : classNames) {
            System.out.println(str);
        }

    }
}
