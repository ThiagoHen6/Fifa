package com.example.fifa.model.dto;

import com.example.fifa.model.Ingresso;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IngressoResponseDTO(
        Long id,
        UsuarioResponseDTO usuario,
        EventoResponseDTO evento,
        LocalDateTime dataCompra,
        BigDecimal valorPago
) {
    public static IngressoResponseDTO toResponse(Ingresso ingresso) {
        return new IngressoResponseDTO(
                ingresso.getId(),
                UsuarioResponseDTO.toResponse(ingresso.getUsuario()),
                EventoResponseDTO.toResponse(ingresso.getEvento()),
                ingresso.getDataCompra(),
                ingresso.getValorPago()
        );
    }
}
