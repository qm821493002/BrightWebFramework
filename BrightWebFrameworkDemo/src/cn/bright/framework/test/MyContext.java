package cn.bright.framework.test;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.carrier.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2014/8/6.
 */
public class MyContext {

    //第一次访问的时候初始化所有带注解的类 path -> className
    private static Map<String, String> actors = new HashMap<String, String>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    //single instance mode
    private static MyContext instance;

    //第五个作用于 bright域  借鉴stucts2 Context和Stack
    BrightDataStore brightDataStore = null;
    private Config config;


    public void initContext() {

            brightDataStore = new BrightDataStore();
            brightDataStore = (BrightDataStore) brightDataStore;



    }


    public static Map<String, String> getActors() {
        return actors;
    }

    public static void setActors(Map<String, String> actors) {
        MyContext.actors = actors;
    }

    public static void addActor(String path, String className) {
        actors.put(path, className);

    }

    public static String getActor(String path) {

        return actors.get(path);
    }

    public static MyContext getInstance() {
        if (instance == null) {

            instance = new MyContext();
        }

        return instance;
    }
    private MyContext(){
        initContext();
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

    public void put(String key, Object object) {
        brightDataStore.put(key, object);
    }

    public Object get(String key) {
        //如果是在初始化的时候调用这个来获得datasotre对象那么这时候这个对象是空
        //简单的返回Null就行了
        if (brightDataStore == null) {

            return null ;
        }
        return brightDataStore.get(key);
    }
}
