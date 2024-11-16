package com.example.demo.infrastructure.validators;

import jakarta.validation.Payload;

public @interface ValidEnum {
    String message() default "invalid value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> enumClass();
}
