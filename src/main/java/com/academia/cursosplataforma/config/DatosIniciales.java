package com.academia.cursosplataforma.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.academia.cursosplataforma.model.Usuario;
import com.academia.cursosplataforma.repository.UsuarioRepository;

@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner crearUsuarios(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                usuarioRepository.save(new Usuario("admin", encoder.encode("admin123"), "ADMIN"));
            }
            if (usuarioRepository.findByUsername("user").isEmpty()) {
                usuarioRepository.save(new Usuario("user", encoder.encode("user123"), "USER"));
            }
        };
    }
}
