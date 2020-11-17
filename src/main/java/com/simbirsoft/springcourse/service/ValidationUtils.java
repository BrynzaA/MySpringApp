package com.simbirsoft.springcourse.service;

import static org.springframework.util.StringUtils.isEmpty;

public class ValidationUtils {
    public static void idIsNotNull(Long id) {
        if (isEmpty(id)) {
            throw new NullPointerException("Id should be not empty");
        }
    }
}
