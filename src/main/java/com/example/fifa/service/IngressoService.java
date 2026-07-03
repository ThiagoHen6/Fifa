package com.example.fifa.service;

import com.example.fifa.exception.EntidadeNaoEncontradaException;
import com.example.fifa.exception.RegraDeNegocioException;
import com.example.fifa.model.Evento;
import com.example.fifa.model.Ingresso;
import com.example.fifa.model.Usuario;
import com.example.fifa.model.dto.IngressoRequestDTO;
import com.example.fifa.model.dto.IngressoResponseDTO;
import com.example.fifa.model.dto.IngressoResumoDTO;
import com.example.fifa.repository.EventoRepository;
import com.example.fifa.repository.IngressoRepository;
import com.example.fifa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngressoService {
    private final IngressoRepository ingressoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public List<IngressoResponseDTO> listar(Long usuario_id) {
        return ingressoRepository.findByUsuarioId(usuario_id)
                .stream()
                .map(IngressoResponseDTO::toResponse)
                .toList();
    }

    public IngressoResponseDTO comprar(IngressoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuario_id())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
        Evento evento = eventoRepository.findById(dto.evento_id())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Evento não encontrado"));
        if (ingressoRepository.countByUsuarioAndEvento(usuario, evento) >= 5) {
            throw new RegraDeNegocioException("Usuário já possui o máximo de ingressos para este evento");
        }
        if (ingressoRepository.countByEvento(evento) >= evento.getCapacidadeMax()) {
            throw new RegraDeNegocioException("Evento já atingiu a capacidade máxima de ingressos");
        }
        Ingresso ingresso = dto.toEntity(usuario, evento);
        Ingresso ingressoSalvo = ingressoRepository.save(ingresso);
        return IngressoResponseDTO.toResponse(ingressoSalvo);
    }

    public IngressoResponseDTO buscarPorId(Long id) {
        return ingressoRepository.findById(id)
                .map(IngressoResponseDTO::toResponse)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ingresso não encontrado"));
    }

    public void cancelar(Long id) {
        Ingresso ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ingresso não encontrado"));
        ingressoRepository.delete(ingresso);
    }

    public IngressoResumoDTO resumo() {
        List<Ingresso> ingressos = ingressoRepository.findAll();
        List<Evento> eventos = eventoRepository.findAll();

        long totalIngressos = ingressos.size();

        BigDecimal receitaTotal = ingressos
                .stream()
                .map(Ingresso::getValorPago)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double taxaOcupacaoMedia = eventos.stream()
                .mapToDouble(evento -> {
                    long vendidos = ingressos.stream()
                            .filter(i -> i.getEvento().getId().equals(evento.getId()))
                            .count();
                    return (double) vendidos / evento.getCapacidadeMax();
                })
                .average()
                .orElse(0.0);

        Evento eventoMaisVendido = eventos.stream()
                .max(Comparator.comparingLong(evento ->
                        ingressos.stream()
                                .filter(i -> i.getEvento().getId().equals(evento.getId()))
                                .count()
                ))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum evento encontrado"));

        return new IngressoResumoDTO(
                totalIngressos,
                receitaTotal,
                taxaOcupacaoMedia,
                eventoMaisVendido.getNome()
        );
    }


}
