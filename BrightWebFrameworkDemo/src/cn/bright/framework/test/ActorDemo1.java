package cn.bright.framework.test;

import cn.bright.webframework.annotations.Actor;

/**
 * Created by hp on 2014/7/30.
 */
public class ActorDemo1 {

    public static void main(String[] args) {

        Class<Acttor1> acttor1Class = Acttor1.class;
        Actor annotation = acttor1Class.getAnnotation(Actor.class);

        System.out.println(annotation);

    }

}
class Acttor1{


}
