package cn.bright.webframework.core;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于把URI解析成为原生的actor motion 和param
 */
public class ParseEngine {

    private BrightContext context;

    public ParseEngine(BrightContext context) {

        this.context = context;
    }


    public String parseToTarget(HttpServletRequest request) {

        String uri = request.getRequestURI();
        if (uri.indexOf("/") != uri.lastIndexOf("/")) {
            uri = uri.substring(0, uri.substring(1).indexOf("/") + 1);
        } else {//如果相等说明就一个/ 那么target就是设置path属性为/的 所以返回/
            uri = "/";
        }
        String actor = context.getActor(uri);

        return actor;

    }

    public String parseToMotion(HttpServletRequest request) {

        String uri = request.getRequestURI();
        return uri.substring(uri.substring(1).indexOf("/") + 1);


    }


}
