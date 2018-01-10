package com.introspector;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * java允许我们可以定义一个对象来描述bean,但是要遵守命名规范,这个对象的类名必须是bean类名后面加上BeanI
 */
public class UltramanBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            return new PropertyDescriptor[]{
                    new PropertyDescriptor("avanta", Ultraman.class),
                    new PropertyDescriptor("name", Ultraman.class)
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}