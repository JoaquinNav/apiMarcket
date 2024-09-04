package com.joaDev.apiMarket.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private List<T> data;
}
