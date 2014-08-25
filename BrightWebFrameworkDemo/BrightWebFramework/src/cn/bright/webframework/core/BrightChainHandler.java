package cn.bright.webframework.core;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.core.director.HeadDirector;
import cn.bright.webframework.exceptions.ModuleClassNoteFoundException;
import cn.bright.webframework.module.Module;
import cn.bright.webframework.module.imp.*;
import sun.awt.ModalityListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理module chain
 */
public class BrightChainHandler {
    private final BrightContext context;
    private List<Module> modules;
    private ActionCarrier actionCarrier;

    private static BrightChainHandler instance;

    private BrightChainHandler(BrightContext context) {
        this.context = context;

        init();
    }

    /**
     * 初始化module链
     */
    private void init() {
        modules = new ArrayList<Module>();
        try {
            //获取modules
            for (String moduleClassName : context.getConfig().getModules()) {
                Class<?> clazz = null;

                clazz = Class.forName(moduleClassName);

                modules.add((Module) clazz.newInstance());
            }
        } catch (Exception e) {
            throw new ModuleClassNoteFoundException("找不到Module类,请确认填写正确", e);
        }

    }


    public void prepare(ActionCarrier actionCarrier) {
        this.actionCarrier = actionCarrier;

    }

    /**
     * 循环处理module 如果处理到最后一个 那么交给director ,dircetor调用actor
     *
     * @throws Exception
     */
    public void next() throws Exception {
        if (actionCarrier.getStep() < modules.size()) {
            Module module = modules.get(actionCarrier.getStep());
            actionCarrier.setStep(actionCarrier.getStep() + 1);
            module.doChain(context, actionCarrier, this);
        } else {
            //调用主逻辑组件执行actor逻辑
            HeadDirector director = new HeadDirector(actionCarrier);
            director.direct();
        }

    }

    public static BrightChainHandler getInstance(BrightContext context, boolean debug) {
        if (!debug) {
            if (instance == null) {

                instance = new BrightChainHandler(context);
            }
            return instance;
        }

        instance = new BrightChainHandler(context);
        return  instance;
    }
}
