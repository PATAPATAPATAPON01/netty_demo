package com.introspector;

import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/10 15:00
 * DESC:
 */
public class TestCase {

    @Test
    public void method() {

//        BeanUtils.copyProperties();

        Class<Superhero> superheroClass = Superhero.class;
        Class<Ultraman> ultramanClass = Ultraman.class;
        System.out.println(superheroClass.isAssignableFrom(ultramanClass));

    }


    @Test
    public void method1() {

        Father father = new Father("father", "2018-01-10", 100);
        Child child = new Child();
        BeanUtils.copyProperties(father, child);
        System.out.println(child);

    }

    @Test
    public void method2() {

        Father father = new Father("father", "2018-01-10", 100);
        Child child = new Child();
//        BeanUtils.copyProperties(father, child);
        BeanCopier copier = BeanCopier.create(Father.class, Child.class, true);
        copier.copy(father, child, new DateConverter("yyyy-MM-dd"));
        System.out.println(child);

    }
}
