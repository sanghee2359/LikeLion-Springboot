package com.springboot.hello.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }
    @GetMapping(value = "/name")
    public String getName() {
        return "Jung sang hee";
    }
    @GetMapping(value="variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }
    @GetMapping(value="variable2/{variable}")
    public String getVariable2(@PathVariable(value="variable") String var){
        return var;
    }
}
