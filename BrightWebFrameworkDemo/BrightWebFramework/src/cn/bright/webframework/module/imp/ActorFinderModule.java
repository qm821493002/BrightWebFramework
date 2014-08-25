package cn.bright.webframework.module.imp;

import cn.bright.webframework.annotations.Actor;
import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.core.BrightChainHandler;
import cn.bright.webframework.core.BrightContext;
import cn.bright.webframework.module.Module;
import cn.bright.webframework.utils.PackageUtil;

import java.net.URL;
import java.util.List;

/**
 * Created by hp on 2014/7/30.
 */
public class ActorFinderModule implements Module {

    private String getActorPath(String className) {

        try {
            Class claszz = Class.forName(className);

            Actor annotation = (Actor) claszz.getAnnotation(Actor.class);
            if (annotation==null){
                return null;
            }else {
                if (annotation.path().equals("")){
                    return "/"+claszz.getSimpleName().toLowerCase();
                }else {
                    return  annotation.path();
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;


    }

    @Override
    public void doChain(BrightContext context, ActionCarrier carrier, BrightChainHandler handler)throws  Exception{
        if (!context.getConfig().isDebug()  &&context.getActors().size()>0){//说明已经初始化过了
            handler.next();
            return ;
        }

        //获取用户的配置文件 如果为空则默认加载类加载路径下所有带注释的控制器
        URL resource = getClass().getClassLoader().getResource("bright-config.xml");

        String packgePath = "";
        if (resource != null) {
            packgePath=context.getConfig().getScanPackage();
        } else {
            packgePath = "classes.";
        }
        List<String> classNames = PackageUtil.getClassNames(packgePath);
        for (String className : classNames) {
            if (className.startsWith("classes.")){

                className=className.substring(className.indexOf(".")+1);
            }
            String actorMapPath = getActorPath(className);
            if (actorMapPath!=null) {
                context.addActor(actorMapPath,className);
            }
        }
        handler.next();

    }
}
