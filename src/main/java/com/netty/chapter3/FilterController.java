package com.netty.chapter3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/9 17:47
 * DESC:
 */
@RestController
public class FilterController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("调用了目标方法");
        return "你好";
    }
}
