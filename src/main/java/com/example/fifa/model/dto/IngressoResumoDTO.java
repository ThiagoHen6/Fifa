package com.example.fifa.model.dto;

import java.math.BigDecimal;

public record IngressoResumoDTO(
        Long totalIngressos,
        BigDecimal receitaTotal,
        Double taxaOcupacaoMedia,
        String eventoMaisVentido
) {
}
