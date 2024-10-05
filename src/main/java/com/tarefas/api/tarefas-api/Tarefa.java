package com.tarefas.tarefas_api;

import java.time.LocalDate;

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

    @Column(name="descricao", columnDefinition="varchar(50)", nullable=false)
    public String descricao;

    @Column(name="prazo", columnDefinition="date", nullable=false)
    public LocalDate prazo;
}
