package com.tarefas.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.UsuarioDTO;
import com.tarefas.api.model.Usuario;
import com.tarefas.api.repository.UsuarioRespository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRespository repoUsuario;

    public UsuarioDTO cadastrarUsuario(Usuario usuario){
        return _converterParaUsuarioDTO(repoUsuario.save(usuario)) ;
    }

    public List<UsuarioDTO> listarUsuario(){
        return repoUsuario.findAll().stream().map(usuario -> _converterParaUsuarioDTO(usuario)).toList();
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

    public List<UsuarioDTO> filtrarUsuarioPeloNome(String nome){
        return repoUsuario.findByNomeContainingIgnoreCase(nome).stream().map(usuario -> _converterParaUsuarioDTO(usuario)).toList();
    }

    public UsuarioDTO filtrarUsuarioPeloEmail(String email){
        Optional<Usuario> usuario = repoUsuario.findByEmail(email);

        if(usuario.isPresent()) {
            return _converterParaUsuarioDTO(usuario.get());
        }
        
        return null;
    }

    public List<UsuarioDTO> filtrarUsuarioPelaDataNascimento(LocalDate dataInicio, LocalDate dataFim){
        return repoUsuario.findByDataNascimentoBetween(dataInicio, dataFim).stream().map(usuario -> _converterParaUsuarioDTO(usuario)).toList();
    }

    public List<UsuarioDTO> filtrarUsuarioInativo(){
        return repoUsuario.findByInativoTrue().stream().map(usuario -> _converterParaUsuarioDTO(usuario)).toList();
    }

    private UsuarioDTO _converterParaUsuarioDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNascimento = usuario.getDataNascimento();
        Period period = Period.between(dataNascimento, dataAtual);

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        dto.setIdade(period.getDays());
        dto.setStatus(usuario.getInativo() ? "INATIVO" : "ATIVO");

        return dto;
    }
}
