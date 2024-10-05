package com.tarefas.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;

    @Column(name="titulo", columnDefinition="varchar(50)", nullable=false)
    public String titulo;

    @Column(name="descricao", columnDefinition="TEXT", nullable=false)
    public String descricao;

    @Column(name="prazo", columnDefinition="date", nullable=true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate prazo;
}
