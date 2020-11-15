package com.simbirsoft.springcourse.service;

public class HelloService {
    public String getHelloInner(String name) {
        String formatter = "Hello %s!";
        if (name != null) {
            return String.format(formatter, name);
        } else {
            return String.format(formatter, "Anonymous");
        }
    }
}
