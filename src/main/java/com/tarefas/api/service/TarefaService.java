package com.tarefas.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefa;
import com.tarefas.api.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    TarefaRepository repoTarefa;

    public TarefaDTO cadastrarTarefa(Tarefa tarefa) {
        return _converterParaTarefaDTO(repoTarefa.save(tarefa));
    }

    public List<TarefaDTO> listarTarefa() {
        return repoTarefa.findAll().stream().map(tarefa -> _converterParaTarefaDTO(tarefa)).toList();
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
        TarefaDTO tarefapesquisada = _converterParaTarefaDTO(this.pesquisarTarefaID(id));
        if(tarefapesquisada != null){
            repoTarefa.deleteById(id);
            return "Tarefa apagada com sucesso!";
        }else {
            return "Tarefa não encontrada!";
        }
    }

    public List<TarefaDTO> filtrarTarefasPeloTitulo(String titulo){
        return repoTarefa.findByTituloContainingIgnoreCase(titulo).stream().map(tarefa -> _converterParaTarefaDTO(tarefa)).toList();
    }

    public List<TarefaDTO> filtrarTarefasPeloPrazo(LocalDate dataInicio, LocalDate dataFim){
        return repoTarefa.findByPrazoBetween(dataInicio, dataFim).stream().map(tarefa -> _converterParaTarefaDTO(tarefa)).toList();
    }

    private TarefaDTO _converterParaTarefaDTO(Tarefa tarefa){
        TarefaDTO dto = new TarefaDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataPrazo = tarefa.getPrazo();
        
        Period period = Period.between(dataAtual, dataPrazo);

        dto.setQtdeDiasRestantes(period.getDays());

        return dto;
    }

}
