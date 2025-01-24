package com.revature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Default controller that gets defaulted to
 */
@RestController
public class DefaultController {

    /**
     * Hello
     * @return says a greeting
     */
    @GetMapping
    public String doSomething(){
        return "Hello";
    }
}
