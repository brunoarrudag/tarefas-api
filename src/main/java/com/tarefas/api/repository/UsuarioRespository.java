package com.tarefas.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.api.model.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    List<Usuario> findByEmail(String email);

    List<Usuario> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFim);
}
