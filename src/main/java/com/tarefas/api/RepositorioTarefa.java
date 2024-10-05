package com.tarefas.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.api.model.Tarefa;

public interface RepositorioTarefa extends JpaRepository<Tarefa, Long>{
    
}
