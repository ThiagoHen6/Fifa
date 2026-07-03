package com.example.fifa.model.dto;

import com.example.fifa.model.Evento;
import com.example.fifa.model.Ingresso;
import com.example.fifa.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IngressoRequestDTO (
        @NotNull Long evento_id,
        @NotNull LocalDateTime dataCompra,
        @NotNull BigDecimal valorPago,
        @NotNull Long usuario_id
) {
    public Ingresso toEntity(Usuario usuario, Evento evento) {
        return new Ingresso(
                null,
                usuario,
                evento,
                dataCompra,
                valorPago
        );
    }
}
