package com.introspector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/10 21:09
 * DESC:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Father {

    private String name;
    private String birthDay;
    private long age;

}
