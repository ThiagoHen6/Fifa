package com.example.fifa.controller;

import com.example.fifa.model.dto.LocalRequestDTO;
import com.example.fifa.model.dto.LocalResponseDTO;
import com.example.fifa.service.LocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @Transactional
    @PostMapping
    public ResponseEntity<LocalResponseDTO> cadastrar(@RequestBody @Valid LocalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<LocalResponseDTO>> listar() {
        return ResponseEntity.ok(localService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(localService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LocalRequestDTO dto) {
        return ResponseEntity.ok(localService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        localService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}