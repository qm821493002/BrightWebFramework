package cn.bright.webframework.module.imp;

import cn.bright.webframework.annotations.Motion;
import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.enums.CommitMethod;
import cn.bright.webframework.module.Module;

import java.lang.reflect.Method;
import java.rmi.ConnectIOException;

/**
 * Created by hp on 2014/7/30.
 */
public class ActorBuildModule implements Module {

    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler) throws Exception {
        Class clazz = Class.forName(carrier.getActionOriginal().getTarget());

        Object instance = clazz.newInstance();

        carrier.setActor(instance);

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {

            Motion methodAnnotation = method.getAnnotation(Motion.class);

            if (methodAnnotation != null) {

                CommitMethod commitMethod = methodAnnotation.method();
                if (commitMethod != CommitMethod.ALL) {
                    if (context.getRequest().getMethod().equals("GET")) {
                        if (commitMethod == commitMethod.POST) {

                            continue;
                        }

                    } else {
                        if (commitMethod == commitMethod.GET) {

                            continue;
                        }
                    }

                }
                if ("".equals(methodAnnotation.path())) {   //如果annotation没有设置path那么默认方法面也可以作为路径
                    if (carrier.getActionOriginal().getMotion().equals("/" + method.getName().toLowerCase())) {
                        carrier.setMotion(method);
                        break;
                    }
                }
                if (carrier.getActionOriginal().getMotion().equals(methodAnnotation.path())) {

                    carrier.setMotion(method);
                    break;
                }
            }

        }

        if (carrier.getMotion() == null) {
            context.getResponse().getWriter().write("404:error!!resource not found!");
            return;
        }

        handler.next();


    }
}
