package com.example.fifa.exception;

import java.time.LocalDateTime;

public record ErroDTO(
        LocalDateTime timestamp,
        Integer status,
        String mensagem
) {}