package cn.bright.webframework.results;

import cn.bright.webframework.carrier.ActionCarrier;

/**
 * Created by hp on 2014/7/30.
 */
public interface MapperResult {

    public void mapperResult(Object result,ActionCarrier actionCarrier)throws Exception;

}
