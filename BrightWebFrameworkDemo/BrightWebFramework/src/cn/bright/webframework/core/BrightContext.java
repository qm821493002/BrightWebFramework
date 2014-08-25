package cn.bright.webframework.core;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.Config;
import cn.bright.webframework.enums.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 框架上下文,保存请求信息
 */
public class BrightContext {

    //第一次访问的时候初始化所有带注解的类 path -> className
    private static Map<String, String> actors = new HashMap<String, String>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    //single instance mode
    private static BrightContext instance;

    //第五个作用于 bright域  借鉴stucts2 Context和Stack
    BrightDataStore brightDataStore = null;
    private Config config;

    /**
     * 初始化上下文对象
     *
     * @param req
     * @param resp
     * @param actionCarrier
     * @param config
     */
    public void initContext(HttpServletRequest req, HttpServletResponse resp, ActionCarrier actionCarrier, Config config) {
        this.setRequest(req);
        this.setResponse(resp);

        this.setConfig(config);

        Object dataStore = req.getAttribute("brightDataStore");

        if (dataStore == null) {
            brightDataStore = new BrightDataStore();
            req.setAttribute("brightDataStore", brightDataStore);
        } else {
            brightDataStore = (BrightDataStore) dataStore;

        }


        //让carries携带上下文 方便以后操作
        actionCarrier.setContext(this);
    }


    public static Map<String, String> getActors() {
        return actors;
    }

    public static void setActors(Map<String, String> actors) {
        BrightContext.actors = actors;
    }

    public static void addActor(String path, String className) {
        actors.put(path, className);

    }

    public static String getActor(String path) {

        return actors.get(path);
    }

    public static BrightContext getInstance() {
        if (instance == null) {

            instance = new BrightContext();
        }

        return instance;
    }

    private BrightContext() {

    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }


    /**
     * 数据访问仓库 我定义的第五个域
     */
    private class BrightDataStore {

        private Map<String, Object> map = new HashMap<String, Object>();

        public void put(String key, Object object) {
            map.put(key, object);
        }

        public Object get(String key) {

            return map.get(key);
        }

    }

    public void put(Scope scope,String key, Object object) {
        switch (scope) {
            case BRIGHT:
                brightDataStore.put(key, object);
                break ;
            case REQUEST:
                request.setAttribute(key, object);
                break ;
            case SESSION:
                request.getSession().setAttribute(key, object);
                break ;
            case APPLICATION:
                request.getServletContext().setAttribute(key, object);
                break ;

        }



    }

    public void put(String key, Object object) {
        brightDataStore.put(key, object);
    }

    public Object get(String key) {
        //如果是在初始化的时候调用这个来获得datasotre对象那么这时候这个对象是空
        //简单的返回Null就行了
        if (brightDataStore == null) {

            return null;
        }
        return brightDataStore.get(key);
    }

}
