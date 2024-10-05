package com.tarefas.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @Column(name="nome", columnDefinition="Varchar(50)",nullable=false)
    public String nome;

    @Column(name="email", columnDefinition="Varchar(50)", nullable=false, unique=true)
    public String email;

    @Column(name="dt_nascimento", columnDefinition="date", nullable=false)
    @JsonFormat(pattern="dd/MM/yyyy")
    public LocalDate dataNascimento;

    @Column(name="inativo", columnDefinition="boolean", nullable=false)
    public Boolean inativo = Boolean.FALSE;

}
