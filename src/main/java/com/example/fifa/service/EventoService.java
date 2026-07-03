package com.example.fifa.service;

import com.example.fifa.exception.EntidadeNaoEncontradaException;
import com.example.fifa.model.Evento;
import com.example.fifa.model.Local;
import com.example.fifa.model.dto.EventoRequestDTO;
import com.example.fifa.model.dto.EventoResponseDTO;
import com.example.fifa.repository.EventoRepository;
import com.example.fifa.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    private final LocalRepository localRepository;

    public EventoResponseDTO cadastrar(EventoRequestDTO dto) {
        Local local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local não encontrado"));
        Evento evento = dto.toEntity(local);
        Evento eventoSalvo = eventoRepository.save(evento);
        return EventoResponseDTO.toResponse(eventoSalvo);
    }

    public List<EventoResponseDTO> listar() {
        return eventoRepository.findAll()
                .stream()
                .map(EventoResponseDTO::toResponse)
                .toList();
    }

    public EventoResponseDTO buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .map(EventoResponseDTO::toResponse)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado"));
    }

    public EventoResponseDTO atualizar(Long id, EventoRequestDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado"));
        Local local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local não encontrado"));
        evento.setNome(dto.nome());
        evento.setLocal(local);
        evento.setData(dto.data());
        evento.setPrecoIngresso(dto.precoIngresso());
        evento.setCapacidadeMax(dto.capacidadeMax());
        Evento eventoAtualizado = eventoRepository.save(evento);
        return EventoResponseDTO.toResponse(eventoAtualizado);
    }

    public void deletar(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado"));
        eventoRepository.delete(evento);
    }
}
