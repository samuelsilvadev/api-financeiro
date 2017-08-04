package com.samuelsilva.api.apifinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuelsilva.api.apifinanceiro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
