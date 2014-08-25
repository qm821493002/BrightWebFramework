package cn.bright.webframework.core.director;

import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.helper.ActorGuide;
import cn.bright.webframework.results.handler.CommonResultHandler;

import java.lang.reflect.Method;

/**
 * Created by hp on 2014/7/30.
 */
public class HeadDirector {

    private ActionCarrier actionCarrier;

    public HeadDirector(ActionCarrier actionCarrier) {
        this.actionCarrier = actionCarrier;
    }

    public void direct() throws Exception{
        Method motion = actionCarrier.getMotion();
        Object actor = actionCarrier.getActor();

        if (actor instanceof ActorGuide) {

            initGuide(actor,actionCarrier);
        }

        Object result= motion.invoke(actor, actionCarrier.getParams());

        //处理结果并且呈现
        CommonResultHandler.getInstance().handle(actionCarrier,result);

    }

    private void initGuide(Object actor, ActionCarrier actionCarrier) {
        ActorGuide actorGuide = (ActorGuide) actor;
        ((ActorGuide) actor).setBrightContext(actionCarrier.getContext());
    }
}
