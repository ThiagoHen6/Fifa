package com.example.fifa.model.dto;

import com.example.fifa.model.Endereco;
import com.example.fifa.model.Role;
import com.example.fifa.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UsuarioRequestDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password,
        @NotNull EnderecoDTO endereco
) {
    public Usuario toEntity(PasswordEncoder encoder) {
        return new Usuario(
                null,
                this.name(),
                this.email(),
                Role.CLIENTE,
                encoder.encode(this.password()),
                new Endereco(
                        null,
                        this.endereco.rua(),
                        this.endereco.numero(),
                        this.endereco.bairro(),
                        this.endereco.cidade(),
                        this.endereco.estado(),
                        this.endereco.cep()
                ),
                null
        );
    }
}
