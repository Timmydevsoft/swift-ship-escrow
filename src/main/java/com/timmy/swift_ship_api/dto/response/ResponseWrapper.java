package com.timmy.swift_ship_api.dto.response;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseWrapper <T>{
    private T data;
    private String message;
    private HttpStatusCode httpStatusCode;
}
