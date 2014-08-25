package cn.bright.framework.domain;

import cn.bright.webframework.annotations.Validator;
import cn.bright.webframework.annotations.validations.Required;

/**
 * Created by hp on 2014/8/7.
 */
@Validator
public class Book{
    @Required
    private String name ;
    private String title ;

    private int age ;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", age=" + age +
                '}';
    }
}
