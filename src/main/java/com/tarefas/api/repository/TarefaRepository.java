package com.tarefas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
