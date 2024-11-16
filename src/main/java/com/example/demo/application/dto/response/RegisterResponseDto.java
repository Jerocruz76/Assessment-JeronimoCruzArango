package com.example.demo.application.dto.response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RegisterResponseDto extends ApiResponseDto {
    public RegisterResponseDto(int value, String string) {
        super(value, string);
    }
}
