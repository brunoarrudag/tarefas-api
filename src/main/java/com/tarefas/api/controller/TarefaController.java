package com.tarefas.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefa;
import com.tarefas.api.service.TarefaService;

@RestController
@RequestMapping(name = "/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService servtarefa;

    @CrossOrigin
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarTarefa(@RequestBody Tarefa tarefa, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ArrayList<String> erros = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> erros.add(error.getDefaultMessage()));
            return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(servtarefa.cadastrarTarefa(tarefa),HttpStatus.OK);
    }

    // Modo 2
    /*@PostMapping("/v2/cadastrar")
    public ResponseEntity<Tarefa> caddastrarTarefas2(@RequestBody Tarefa tarefa){
        return ResponseEntity.status(HttpStatus.CREATED).body(servtarefa.cadastrarTarefa(tarefa));
    } */

    @GetMapping("/listar")
    public List<TarefaDTO> listarTarefa() {
        return servtarefa.listarTarefa();
    }

    // Modo 2 
    /*@GetMapping("/v2/listar")
    public ResponseEntity<List<Tarefa>> listarTarefa2() {
        return ResponseEntity.ok().body(servtarefa.listarTarefa());
    }*/

    @GetMapping("/listar/{id}")
    public Tarefa pesquisarTarefaID(@PathVariable Long id) {
        return servtarefa.pesquisarTarefaID(id);
    }

    // Modo 2
    /*@GetMapping("/v2/listar/{id}")
    public ResponseEntity<Tarefa> pesquisarTarefaID2(@PathVariable("id") Long id) {
        Tarefa tarefa = servtarefa.pesquisarTarefaID(id);
        if ( Objects.isNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(tarefa);
    }*/

    // TO-DO, Gerar o Modo 2 baseado nos acima
    @PutMapping("/atualizar/{id}")
    public String atualizarTarefaId(@PathVariable Long id, @RequestBody Tarefa tarefanova) {
        return servtarefa.atualizarTarefa(id, tarefanova);
    }
    
    // TO-DO, Gerar o Modo 2 baseado nos acima
    @DeleteMapping("/apagar/{id}")
    public String apagarTarefaID(@PathVariable Long id) {
        return servtarefa.apagarTarefa(id);
    }

    @GetMapping("/v2/fitrotitulo")
    public ResponseEntity<List<TarefaDTO>> pesquisarTarefaTitulo(@RequestParam("titulo") String titulo) {
       return ResponseEntity.ok().body(servtarefa.filtrarTarefasPeloTitulo(titulo));
    }

    @GetMapping("/v2/filtroprazo")
    public ResponseEntity<List<TarefaDTO>> pesquisarTarefaTitulo(@RequestParam("dataInicio") LocalDate DataInicio, @RequestParam("dataFim") LocalDate DataFim) {
       return ResponseEntity.ok().body(servtarefa.filtrarTarefasPeloPrazo(DataInicio, DataFim));
    }

}
