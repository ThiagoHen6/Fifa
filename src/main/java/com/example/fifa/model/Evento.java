package com.example.fifa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_id")
    private Local local;
    private LocalDateTime data;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Ingresso> ingressos = new ArrayList<>();
    private String nome;
    private BigDecimal precoIngresso;
    private Integer capacidadeMax;
}
