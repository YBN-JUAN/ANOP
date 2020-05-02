package edu.fzu.anop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello控制器
 *
 * @author Xue_Feng
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    String hello() {
        return "hello world";
    }
}
