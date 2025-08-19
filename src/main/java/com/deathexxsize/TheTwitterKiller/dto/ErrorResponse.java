package com.deathexxsize.TheTwitterKiller.dto;


import java.time.LocalDateTime;

public record ErrorResponse (
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp
) {}
