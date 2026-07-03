package com.example.fifa.model.dto;

import com.example.fifa.model.Local;

public record LocalResponseDTO(
        Long id,
        String tipo,
        String nome,
        Integer capacidadeMax,
        String rua,
        String cidade,
        String estado
) {
    public static LocalResponseDTO toResponse(Local local) {
        return new LocalResponseDTO(
                local.getId(),
                local.getTipo(),
                local.getNome(),
                local.getCapacidadeMax(),
                local.getRua(),
                local.getCidade(),
                local.getEstado()
        );
    }
}
