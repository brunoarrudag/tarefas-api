package com.tarefas.tarefas_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class ControladorTarefa {
    
    @Autowired
    ServicoTarefa servtarefa;

    @CrossOrigin
    @PostMapping("/cadastrarTarefa")
    public ResponseEntity<?> cadastrarDisciplina(@RequestBody Tarefa tarefa, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ArrayList<String> erros = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> erros.add(error.getDefaultMessage()));
            return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(servtarefa.CadastrarTarefa(tarefa),HttpStatus.OK);
    }

    @GetMapping("/listarTarefaAll")
    public List<Tarefa> listarTarefa() {
        return servtarefa.listarTarefa();
    }

    @GetMapping("/listarTarefa/{id}")
    public Optional<Tarefa> pesquisarTarefaID(@PathVariable Long id) {
        return servtarefa.pesquisarTarefaID(id);
    }

    @PutMapping("/atualizarTarefa/{id}")
    public String atualizarTarefaId(@PathVariable Long id, @RequestBody Tarefa tarefanova) {
        return servtarefa.atualizarTarefa(id, tarefanova);
    }

    @DeleteMapping("/apagarTarefa/{id}")
    public String apagarTarefaID(@PathVariable Long id) {
        return servtarefa.apagarTarefa(id);
    }
}
