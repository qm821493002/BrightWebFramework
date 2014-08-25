package cn.bright.webframework.results.imp;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.results.MapperResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hp on 2014/7/31.
 */
public class StringMapperResult implements MapperResult {
    private static final String FORWARD = "forward";
    private static final String REDIRECT = "redirect";

    @Override
    public void mapperResult(Object result, ActionCarrier actionCarrier) throws Exception {
        String strResult = ((String) result);
        if (strResult.contains(":")) {
            handlePattern(strResult, actionCarrier);
        } else {

            if (strResult.endsWith(".jsp")) {//处理jsp
                handleNoPattern(strResult, actionCarrier);
            }

        }

    }

    private void handleNoPattern(String strResult, ActionCarrier actionCarrier) throws Exception {
        if (!strResult.startsWith("/")) {
            strResult = "/" + strResult;
        }
        HttpServletResponse response = actionCarrier.getContext().getResponse();
        HttpServletRequest request = actionCarrier.getContext().getRequest();
        request.getRequestDispatcher(strResult).forward(request, response);

    }

    private void handlePattern(String strResult, ActionCarrier actionCarrier) throws Exception {
        String[] split = strResult.split(":");
        String pattern = split[0];
        String target = "";
        if (!split[1].startsWith("/")) {
            target = "/" + split[1];
        } else {
            target = split[1];
        }
        HttpServletResponse response = actionCarrier.getContext().getResponse();
        HttpServletRequest request = actionCarrier.getContext().getRequest();
        if (pattern.startsWith(FORWARD)) {

            request.getRequestDispatcher(target).forward(request, response);
            return;
        }
        if (pattern.startsWith(REDIRECT)) {
            response.sendRedirect(target);
            return;
        }

    }
}
