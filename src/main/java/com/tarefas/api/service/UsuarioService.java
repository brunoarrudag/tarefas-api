package com.tarefas.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.model.Usuario;
import com.tarefas.api.repository.UsuarioRespository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRespository repoUsuario;

    public Usuario cadastrarUsuario(Usuario usuario){
        return repoUsuario.save(usuario);
    }

    public List<Usuario> listarUsuario(){
        return repoUsuario.findAll();
    }

    public Usuario listarUsuarioID(Long id){
        Optional<Usuario> usuarioPesquisado = repoUsuario.findById(id);
        if (usuarioPesquisado.isPresent()) {
            return usuarioPesquisado.get();
        } else {
            return null;
        } 
    }

    public String atualizarUsuario(Long id, Usuario novoUsuario){
        Usuario usuarioPesquisado = this.listarUsuarioID(id);
        if(usuarioPesquisado != null){
            usuarioPesquisado.nome = novoUsuario.nome;
            usuarioPesquisado.email = novoUsuario.email;
            usuarioPesquisado.dataNascimento = novoUsuario.dataNascimento;
            usuarioPesquisado.inativo = novoUsuario.inativo;
            repoUsuario.save(usuarioPesquisado);
            return "Usuario alterado com Sucesso!";
        }else {
            return "Usuario não encontrado!";
        }
    }

    public String apagarUsuario(Long id){
        Usuario usuarioPesquisado = this.listarUsuarioID(id);
        if(usuarioPesquisado != null){
            repoUsuario.deleteById(id);
            return "Usuario apagado com sucesso!";
        }else {
            return "Usuario não encontrado!";
        }
    }

    public List<Usuario> filtrarUsuarioPeloNome(String nome){
        return repoUsuario.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Usuario> filtrarUsuarioPeloEmail(String email){
        return repoUsuario.findByEmail(email);
    }

    public List<Usuario> filtrarUsuarioPelaDataNascimento(LocalDate dataInicio, LocalDate dataFim){
        return repoUsuario.findByDataNascimentoBetween(dataInicio, dataFim);
    }

    public List<Usuario> filtrarUsuarioInativo(){
        return repoUsuario.findByInativoTrue();
    }

}
