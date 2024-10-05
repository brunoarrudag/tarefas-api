package com.tarefas.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.model.Tarefa;
import com.tarefas.api.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    TarefaRepository repoTarefa;

    public Tarefa cadastrarTarefa(Tarefa tarefa) {
        return repoTarefa.save(tarefa);
    }

    public List<Tarefa> listarTarefa() {
        return repoTarefa.findAll();
    }

    public Tarefa pesquisarTarefaID(Long id){
        Optional<Tarefa> tarefapesquisada = repoTarefa.findById(id);
        if (tarefapesquisada.isPresent()) {
            return tarefapesquisada.get();
        } else {
            return null;
        }
        
    }

    public String atualizarTarefa(Long id, Tarefa tarefanova){
        Tarefa tarefapesquisada = this.pesquisarTarefaID(id);
        if(tarefapesquisada != null){
            tarefapesquisada.titulo = tarefanova.titulo;
            tarefapesquisada.descricao = tarefanova.descricao;
            tarefapesquisada.prazo = tarefanova.prazo;
            repoTarefa.save(tarefapesquisada);
            return "Tarefa alterada com Sucesso!";
        }else {
            return "Tarefa não encontrada!";
        }
    }

    public String apagarTarefa(Long id){
        Tarefa tarefapesquisada = this.pesquisarTarefaID(id);
        if(tarefapesquisada != null){
            repoTarefa.deleteById(id);
            return "Tarefa apagada com sucesso!";
        }else {
            return "Tarefa não encontrada!";
        }
    }

    public List<Tarefa> filtrarTarefasPeloTitulo(String titulo){
        return repoTarefa.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Tarefa> filtrarTarefasPeloPrazo(LocalDate dataInicio, LocalDate dataFim){
        return repoTarefa.findByPrazoBetween(dataInicio, dataFim);
    }
}
