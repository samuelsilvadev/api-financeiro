package com.samuelsilva.api.apifinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuelsilva.api.apifinanceiro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
