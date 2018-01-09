package com.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/9 15:33
 * DESC:
 */
@RestController
public class FilterTestController {

    @GetMapping("/hello")
    public String sayHello() {

        return "你好";
    }
}
