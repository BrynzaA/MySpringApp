package com.simbirsoft.springcourse.controller;

import com.simbirsoft.springcourse.service.HelloService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    HelloService helloService;

    // URL example http://localhost:8080/hello?name=Bob
    @GetMapping(value = "hello")
    public String getHelloWithNameParam(@RequestParam(value = "name", required = false) String name) {
        return helloService.getHelloInner(name);
    }

    // URL example http://localhost:8080/hello/Bob
    @GetMapping(value = "hello/{name}")
    public String getHelloWithPathParam(@PathVariable(value = "name", required = true) String name) {
        return helloService.getHelloInner(name);
    }
}
