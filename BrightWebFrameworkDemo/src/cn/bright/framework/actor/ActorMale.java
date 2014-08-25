package cn.bright.framework.actor;

import cn.bright.framework.domain.User;
import cn.bright.webframework.annotations.Actor;
import cn.bright.webframework.annotations.Motion;
import cn.bright.webframework.annotations.Outfit;
import cn.bright.webframework.carrier.FileDomain;
import cn.bright.webframework.core.BrightContext;

import java.io.*;
import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2014/7/30.
 */
@Actor
public class ActorMale {
    @Motion(path = "/addUser")
    public String addUser(@Outfit User user) {
        BrightContext.getInstance().getRequest().setAttribute("name", "admin");
        BrightContext.getInstance().put("user", user);

        return "forward:index.jsp";
    }

    @Motion(path = "/")
    public void index() {

        System.out.println("xixixi");
    }

    @Motion()
    public String users(BrightContext brightContext) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("admin1");
        list.add("admin2");
        list.add("admin3");
        brightContext.getRequest().setAttribute("list",list);
        return "index.jsp";
    }

    @Motion(path = "/upload")
    public String uploadTest(Map<String, String[]> map, FileDomain fileDomain) throws Exception {
        File file = fileDomain.getFile();
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(new File("c://temp1.txt"));
        int len = 0;
        byte[] buffer = new byte[1024];

        while ((len = inputStream.read(buffer)) > 0) {

            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        ;
        outputStream.close();
        ;

        return "forward:index.jsp";
    }
}
