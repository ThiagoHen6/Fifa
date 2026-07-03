package com.example.fifa.controller;

import com.example.fifa.model.dto.IngressoRequestDTO;
import com.example.fifa.model.dto.IngressoResponseDTO;
import com.example.fifa.model.dto.IngressoResumoDTO;
import com.example.fifa.service.IngressoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
@RequiredArgsConstructor
public class IngressoController {

    private final IngressoService ingressoService;

    @Transactional
    @PostMapping
    public ResponseEntity<IngressoResponseDTO> comprar(@RequestBody @Valid IngressoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingressoService.comprar(dto));
    }

    @GetMapping
    public ResponseEntity<List<IngressoResponseDTO>> listar(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(ingressoService.listar(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngressoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ingressoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        ingressoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumo")
    public ResponseEntity<IngressoResumoDTO> resumo() {
        return ResponseEntity.ok(ingressoService.resumo());
    }
}