package cn.bright.webframework.core;

import cn.bright.webframework.core.decorator.CGLIbApplicationWrap;
import cn.bright.webframework.core.decorator.CGLIbParamsWrap;
import cn.bright.webframework.core.decorator.CGLIbRequestWrap;
import cn.bright.webframework.core.decorator.CGLIbSessionWrap;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.AbstractContext;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hp on 2014/8/6.
 */
public class ExpressionEngine {
    private static ExpressionEngine instance;
    private static FelEngine fel;

    public static ExpressionEngine getInstance() {
        if (instance == null) {

            instance = init();

        }
        return instance;
    }

    private static ExpressionEngine init() {
        ExpressionEngine expressionEngine = new ExpressionEngine();

        ExpressionEnginContext expressionContext = new ExpressionEnginContext();
        fel = new FelEngineImpl(expressionContext);

        return expressionEngine;
    }

    public Object eval(String expression) {
        Object eval = fel.eval(expression);


        return eval;


    }

    private static class ExpressionEnginContext extends AbstractContext {
        BrightContext brightContext;

        CGLIbRequestWrap request;
        CGLIbSessionWrap session;
        CGLIbApplicationWrap application;
        CGLIbParamsWrap params;

        public ExpressionEnginContext() {
            brightContext = BrightContext.getInstance();
            //初始化各个包装类
            request = new CGLIbRequestWrap(brightContext.getRequest());
            session = new CGLIbSessionWrap(brightContext.getRequest().getSession());
            application = new CGLIbApplicationWrap(brightContext.getRequest().getServletContext());
            params = new CGLIbParamsWrap(brightContext.getRequest());

        }

        @Deprecated
        public static Object newInstance(Class clazz, Callback callback, Object param) {
            try {

                Enhancer enhancer = new Enhancer();

                enhancer.setSuperclass(clazz);
                enhancer.setCallback(callback);


                Object beanObject = enhancer.create(new Class[]{param.getClass()}, new Object[]{param});

                return beanObject;

            } catch (Exception e) {
                e.printStackTrace();
                throw new Error(e.getMessage());
            }

        }


        @Override
        public Object get(String s) {

            Object obj = null;
            if ("request".equals(s)) {

                obj = request;
            } else if ("session".equals(s)) {
                obj = session;
            } else if ("application".equals(s)) {
                obj = application;
            } else if ("params".equals(s)) {
                obj = params;
            } else {
                obj = brightContext.get(s);
            }


            return obj;

        }

        @Deprecated
        private class PropertyIntercept implements MethodInterceptor {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                System.out.println("interceptor:" + method.getName());
                return methodProxy.invokeSuper(object, args);
            }
        }

    }


}
