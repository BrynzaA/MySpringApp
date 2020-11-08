package com.simbirsoft.springcourse;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    // URL example http://localhost:8080/hello?name=Bob
    @GetMapping(value = "hello")
    public String getHelloWithNameParam(@RequestParam(value = "name", required = false) String name) {
        return getHelloInner(name);
    }

    // URL example http://localhost:8080/hello/Bob
    @GetMapping(value = "hello/{name}")
    public String getHelloWithPathParam(@PathVariable(value = "name", required = true) String name) {
        return getHelloInner(name);
    }

    // If name is empty, return Anonymous
    private String getHelloInner(String name) {
        String formatter = "Hello %s!";
        if (name != null) {
            return String.format(formatter, name);
        } else {
            return String.format(formatter, "Anonymous");
        }
    }
}
