package cn.bright.framework.domain;

import cn.bright.webframework.annotations.Validator;
import cn.bright.webframework.annotations.validations.Regex;
import cn.bright.webframework.annotations.validations.Required;

/**
 * Created by hp on 2014/7/31.
 */
@Validator
public class User {
    @Required
    private String name;
    @Regex("\\d{6}a")
    private String password;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
