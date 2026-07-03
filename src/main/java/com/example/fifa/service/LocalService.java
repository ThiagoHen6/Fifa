package com.example.fifa.service;

import com.example.fifa.exception.EntidadeNaoEncontradaException;
import com.example.fifa.model.Local;
import com.example.fifa.model.dto.LocalRequestDTO;
import com.example.fifa.model.dto.LocalResponseDTO;
import com.example.fifa.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalService {
    private final LocalRepository localRepository;

    public LocalResponseDTO cadastrar(LocalRequestDTO dto) {
        Local local = dto.toEntity();
        Local localSalvo = localRepository.save(local);
        return LocalResponseDTO.toResponse(localSalvo);
    }

    public List<LocalResponseDTO> listar() {
        return localRepository.findAll()
                .stream()
                .map(LocalResponseDTO::toResponse)
                .toList();
    }

    public LocalResponseDTO atualizar(Long id, LocalRequestDTO dto) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local não encontrado"));
        local.setTipo(dto.tipo());
        local.setNome(dto.nome());
        local.setCapacidadeMax(dto.capacidadeMax());
        local.setRua(dto.rua());
        local.setCidade(dto.cidade());
        local.setEstado(dto.estado());
        Local localAtualizado = localRepository.save(local);
        return LocalResponseDTO.toResponse(localAtualizado);
    }

    public LocalResponseDTO buscarPorId(Long id) {
        return localRepository.findById(id)
                .map(LocalResponseDTO::toResponse)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local não encontrado"));
    }

    public void deletar(Long id) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Local não encontrado"));
        localRepository.delete(local);
    }
}
