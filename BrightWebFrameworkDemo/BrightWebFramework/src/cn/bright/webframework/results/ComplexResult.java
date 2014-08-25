package cn.bright.webframework.results;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2014/8/7.
 */
public class ComplexResult {

    Map<String, Object> map = new HashMap<String, Object>();
    String path ;

    public ComplexResult(String path ){
        this.path= path ;
    }
    public ComplexResult(String path , String key ,Object value){
        this.path= path ;
        map.put(key,value)  ;
    }
    public ComplexResult(String path,Map<String,Object> map){
        this.path= path ;
        this.map = map;
    }

    public void addOutfit(String key, Object object) {

        map.put(key, object);

    }

    public Object removeOutfit(String key) {
        return map.remove(key);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
