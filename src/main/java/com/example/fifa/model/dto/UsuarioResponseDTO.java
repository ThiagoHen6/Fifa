package com.example.fifa.model.dto;

import com.example.fifa.model.Role;
import com.example.fifa.model.Usuario;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String name,
        String email,
        Role role,
        EnderecoDTO endereco
) {
    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getRole(),
                new EnderecoDTO(
                        usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getBairro(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado(),
                        usuario.getEndereco().getCep()
                )
        );
    }
}
