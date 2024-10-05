package com.tarefas.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);

    List<Tarefa> findByPrazoBetween(LocalDate dataInicio, LocalDate dataFim);
}
