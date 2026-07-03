package com.example.fifa.model.dto;

import com.example.fifa.model.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalRequestDTO(
        @NotBlank String tipo,
        @NotBlank String nome,
        @NotNull Integer capacidadeMax,
        @NotBlank String rua,
        @NotBlank String cidade,
        @NotBlank String estado
) {
    public Local toEntity() {
        return new Local(
                null,
                this.tipo(),
                this.nome(),
                this.capacidadeMax(),
                this.rua(),
                this.cidade(),
                this.estado()
        );
    }
}
