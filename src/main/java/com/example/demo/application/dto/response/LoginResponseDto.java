package com.example.demo.application.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginResponseDto extends ApiResponseDto{
    private LoginResponseDataDto data;

    private LoginResponseDto(int value, String string, LoginResponseDataDto data){
        super(value, string);
        this.data = data;
    }
}
