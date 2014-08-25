package cn.bright.framework.actor;

import cn.bright.webframework.annotations.Actor;
import cn.bright.webframework.annotations.Motion;
import cn.bright.webframework.annotations.validations.Required;
import cn.bright.webframework.enums.Scope;
import cn.bright.webframework.helper.ActorGuide;

/**
 * Created by hp on 2014/8/5.
 */
@Actor
public class ActorFemale extends ActorGuide {


    @Motion
    public String test() {
        put(Scope.SESSION,"name","session scope is ok !");
        return  forward("index.jsp");
    }

}
