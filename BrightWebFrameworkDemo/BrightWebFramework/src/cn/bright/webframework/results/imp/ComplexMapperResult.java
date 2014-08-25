package cn.bright.webframework.results.imp;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.results.ComplexResult;
import cn.bright.webframework.results.MapperResult;

import java.util.Map;

/**
 * Created by hp on 2014/8/7.
 */
public class ComplexMapperResult implements MapperResult {
    @Override
    public void mapperResult(Object result, ActionCarrier actionCarrier) throws Exception {

        ComplexResult complexResult = (ComplexResult) result;
        Map<String, Object> map = complexResult.getMap();
        for (String str : map.keySet()) {
            Object value = map.get(str);
            actionCarrier.getContext().put(str, value);
        }

        //转交给StringMapper处理
        String path = complexResult.getPath();
        new StringMapperResult().mapperResult(path, actionCarrier);
    }
}
