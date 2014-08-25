package cn.bright.webframework.module.imp;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.core.ParseEngine;
import cn.bright.webframework.module.Module;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hp on 2014/7/30.
 */
public class URIParseModule implements Module {
    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler) throws Exception {
        ParseEngine parseEngine = new ParseEngine(context);

        //解析路径为目标演员,motion以及参数信息
        String target = parseEngine.parseToTarget(context.getRequest());
        String motion = parseEngine.parseToMotion(context.getRequest());
        Map<String, String[]> params = context.getRequest().getParameterMap();


        if (target == null) {

            context.getResponse().getWriter().write("404:error!!resource not found!");
            return;
        }
        carrier.getActionOriginal().setTarget(target);
        carrier.getActionOriginal().setMotion(motion);
        carrier.getActionOriginal().setParams(params);


        handler.next();
    }
}
