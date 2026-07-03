package com.example.fifa.model.dto;

import com.example.fifa.model.Evento;
import com.example.fifa.model.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventoRequestDTO(
        @NotNull LocalDateTime data,
        @NotBlank String nome,
        @NotNull BigDecimal precoIngresso,
        @NotNull Integer capacidadeMax,
        @NotNull Long localId
) {
    public Evento toEntity(Local local) {
        return new Evento(
                null,
                local,
                data,
                null,
                nome,
                precoIngresso,
                capacidadeMax
        );
    }
}
