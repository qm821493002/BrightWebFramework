package cn.bright.framework.actor;

import cn.bright.framework.domain.Book;
import cn.bright.framework.domain.User;
import cn.bright.webframework.annotations.Actor;
import cn.bright.webframework.annotations.Motion;
import cn.bright.webframework.annotations.Outfit;
import cn.bright.webframework.carrier.ActionCarrier;
import cn.bright.webframework.enums.CommitMethod;
import cn.bright.webframework.helper.ActorGuide;

/**
 * Created by hp on 2014/8/7.
 */
@Actor(path = "/")
public class ActorDemo2 extends ActorGuide {

    @Motion(method = CommitMethod.GET, path = "/index")
    public Object index(@Outfit Book book,ActionCarrier carrier) {
        System.out.println(carrier.getWarning());
        System.out.println(book);
        return forward("Demo2.jsp","book",book);
    }
}
