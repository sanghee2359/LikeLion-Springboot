package com.springboot.hello.Controller;

import com.springboot.hello.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {
    private MemberDto memberDto;
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

    @GetMapping(value="/request1")
    public String getVariable3(@RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return String.format("%s %s %s", name, email,organization);
    }
    @GetMapping(value="/request2")
    public String getVariable3(@RequestParam Map<String,String> param){
        param.entrySet().forEach((map)->{
            System.out.printf("key:%s value:%s", map.getKey(), map.getValue());
        });
        return "request2가 호출 완료 되었습니다.";
    }

    @GetMapping(value="/request3")
    public String getRequestParam(MemberDto memberDto){
        return memberDto.toString();
    }

}
