package cn.bright.webframework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hp on 2014/7/30.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public  @interface Actor {
    String path() default "";

}
