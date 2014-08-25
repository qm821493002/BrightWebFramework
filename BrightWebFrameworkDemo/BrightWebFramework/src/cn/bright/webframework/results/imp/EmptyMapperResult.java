package cn.bright.webframework.results.imp;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.results.MapperResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hp on 2014/7/31.
 */
public class EmptyMapperResult implements MapperResult {


    @Override
    public void mapperResult(Object result, ActionCarrier actionCarrier) throws  Exception{
        HttpServletResponse response = actionCarrier.getContext().getResponse();
        HttpServletRequest request = actionCarrier.getContext().getRequest();

        String requestURI = request.getRequestURI();

        response.sendRedirect("");


    }
}
