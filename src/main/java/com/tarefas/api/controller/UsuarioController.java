package com.tarefas.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.model.Usuario;
import com.tarefas.api.service.UsuarioService;

@RestController
@RequestMapping(name = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService servUsuario;

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok().body(servUsuario.listarUsuario());
    }

    @GetMapping("/listarUSuario/{id}")
    public ResponseEntity<Usuario> listarUsuarioPorID(@PathVariable("id") Long id) {
        Usuario usuario = servUsuario.listarUsuarioID(id);
        if ( Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(usuario);
    }
    
    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<Usuario> caddastrarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servUsuario.cadastrarUsuario(usuario));
    }

    // TO-DO, Gerar o Modo 2 baseado nos acima
    @PutMapping("/atualizarUsuario/{id}")
    public String atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        return servUsuario.atualizarUsuario(id, novoUsuario);
    }
    
    // TO-DO, Gerar o Modo 2 baseado nos acima
    @DeleteMapping("/apagarUsuario/{id}")
    public String apagarUsuario(@PathVariable Long id) {
        return servUsuario.apagarUsuario(id);
    }

}
