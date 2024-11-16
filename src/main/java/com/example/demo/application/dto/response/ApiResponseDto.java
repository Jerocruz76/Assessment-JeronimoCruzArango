package com.example.demo.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public abstract class ApiResponseDto {
    private Integer status;
    private String message;
}
