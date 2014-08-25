package cn.bright.webframework.core;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.Config;
import cn.bright.webframework.core.decorator.BrightServletRequest;
import cn.bright.webframework.core.decorator.BrightServletResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 核心控制器,拦截所有请求并且分发给各个Actor
 */
@WebFilter(filterName = "BrightEntryFilter")
public class BrightEntryFilter implements Filter {
    private BrightContext context;
    private boolean debug  =false;

    public void init(FilterConfig config) throws ServletException {

        //判断是否开启调试模式 (开启后每次请求都讲加载XML文件)
        String debugStr = config.getInitParameter("debug");
        if (debugStr != null && debugStr.trim().equals("true")) {
            debug=true;
        }

        context = BrightContext.getInstance();




    }



    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            //包裹Request和Response
            BrightServletRequest  request = new BrightServletRequest((HttpServletRequest) req);
            BrightServletResponse response= new BrightServletResponse((HttpServletResponse) resp);


            //创建携带者贯穿请求相应流程
            ActionCarrier actionCarrier = new ActionCarrier();

            //解析配置文件
            Config config = ConfigEngine.loadConfig(debug);
            config.setDebug(debug);

            //初始化上下文
           context.initContext(request, response, actionCarrier,config);

            //判断是否需要放行(eg : jsp , js,css)
           if (discharged(actionCarrier,config)) {
               chain.doFilter(request, response);
               return;
           }

            //可插拔链式处理
            BrightChainHandler      brightChainHandler = BrightChainHandler.getInstance(context, debug);
            brightChainHandler.prepare(actionCarrier);
            brightChainHandler.next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 判断是否要放行请求
     * @param actionCarrier
     * @param config
     * @return true 为放行
     */
    private boolean discharged(ActionCarrier actionCarrier, Config config) {
        String requestURI = context.getRequest().getRequestURI();

        for (String allclears : config.getAllclears()) {
            if (requestURI.endsWith(allclears)) {
                return true;
            }
        }
        return false ;
    }





    public void destroy() {
    }

}
