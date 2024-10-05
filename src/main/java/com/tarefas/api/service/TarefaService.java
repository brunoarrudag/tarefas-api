package com.tarefas.api.service;

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

    public Tarefa CadastrarTarefa(Tarefa tarefa) {
        return repoTarefa.save(tarefa);
    }

    public List<Tarefa> listarTarefa() {
        return repoTarefa.findAll();
    }

    public Optional<Tarefa> pesquisarTarefaID(Long id){
        return repoTarefa.findById(id);
    }

    public String atualizarTarefa(Long id, Tarefa tarefanova){
        Optional<Tarefa> tarefapesquisada = this.pesquisarTarefaID(id);
        if(tarefapesquisada != null){
            tarefapesquisada.get().titulo = tarefanova.titulo;
            tarefapesquisada.get().descricao = tarefanova.descricao;
            tarefapesquisada.get().prazo = tarefanova.prazo;
            repoTarefa.save(tarefapesquisada.get());
            return "Aluno Alterado com Sucesso!";
        }else {
            return "Aluno não encontrado!";
        }
    }

    public String apagarTarefa(Long id){
        Optional<Tarefa> tarefapesquisada = this.pesquisarTarefaID(id);
        if(tarefapesquisada != null){
            repoTarefa.deleteById(id);
            return "Aluno apagado com sucesso!";
        }else {
            return "Aluno não encontrado!";
        }
    }
}
