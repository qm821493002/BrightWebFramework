package cn.bright.webframework.module;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;

import java.io.IOException;

/**
 * Created by hp on 2014/7/30.
 */
public interface Module {

    public void doChain(BrightContext context,ActionCarrier carrier,BrightChainHandler handler) throws Exception;
}
