package cn.bright.framework.test;

import cn.bright.framework.domain.User;

/**
 * Created by hp on 2014/8/6.
 */
public class ExpressionEngine {
    MyContext myContext = MyContext.getInstance();

    {
        User user = new User();
        user.setName("qiming");
        user.setPassword("walalla");
        user.setAge(18);

        myContext.put("user",user);
        myContext.put("name", "jim");
        myContext.put("passwrod","123546a");
    }

    public Object parse(String expression){



        return null;
    }
}
