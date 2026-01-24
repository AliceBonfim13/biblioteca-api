package com.alice.libraryapi.repository;

import com.alice.libraryapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByLogin(String id);

    Usuario findByEmail(String email);
}
