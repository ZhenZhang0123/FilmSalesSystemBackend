package com.zhenzhang0123.filmsalessystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> Response<T> error(int status, String message) {
        return Response.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(message)
                .build();
    }
}
