package com.example.fifa.repository;

import com.example.fifa.model.Evento;
import com.example.fifa.model.Ingresso;
import com.example.fifa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    long countByEvento(Evento evento);
    long countByUsuarioAndEvento(Usuario usuario, Evento evento);
    List<Ingresso> findByUsuarioId(Long usuario_id);
}
