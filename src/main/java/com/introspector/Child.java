package com.introspector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/10 21:10
 * DESC:
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Child {

    private String name;
    private Date birthDay;
    private long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
