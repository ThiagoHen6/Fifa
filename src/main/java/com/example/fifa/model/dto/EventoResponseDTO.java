package com.example.fifa.model.dto;

import com.example.fifa.model.Evento;
import com.example.fifa.model.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDateTime data,
        BigDecimal precoIngresso,
        Integer capacidadeMax,
        LocalResponseDTO local
) {
    public static EventoResponseDTO toResponse(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getPrecoIngresso(),
                evento.getCapacidadeMax(),
                LocalResponseDTO.toResponse(evento.getLocal())
        );
    }
}
