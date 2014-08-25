package cn.bright.webframework.annotations;

import cn.bright.webframework.enums.CommitMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hp on 2014/7/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Motion {
    String path() default "";
    CommitMethod method() default  CommitMethod.ALL;

}
