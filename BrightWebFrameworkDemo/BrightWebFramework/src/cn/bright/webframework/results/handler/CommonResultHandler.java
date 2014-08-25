package cn.bright.webframework.results.handler;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.results.ComplexResult;
import cn.bright.webframework.results.imp.ComplexMapperResult;
import cn.bright.webframework.results.imp.EmptyMapperResult;
import cn.bright.webframework.results.imp.StringMapperResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

/**
 * Created by hp on 2014/7/30.
 */
public class CommonResultHandler {

    private static CommonResultHandler instance;

    private CommonResultHandler() {

    }

    public static CommonResultHandler getInstance() {
        if (instance == null) {

            instance = new CommonResultHandler();
        }

        return instance;

    }

    public void handle(ActionCarrier actionCarrier, Object result) throws Exception {
        if (result == null) {
            new EmptyMapperResult().mapperResult(result, actionCarrier);

            return;
        }
        //对carries返回的结果根据类型进行动态处理
        if (result instanceof String) {
            new StringMapperResult().mapperResult(result, actionCarrier);
            return ;
        }

        if (result instanceof ComplexResult) {
            new ComplexMapperResult().mapperResult(result, actionCarrier);
            return ;
        }
    }
}

