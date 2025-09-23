package com.streaming.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(columnNames = {"nome"}))
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length=255, unique=true)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    public Categoria(){}
    public Categoria(String nome){ this.nome = nome; }

    public Integer getId(){return id;}
    public String getNome(){return nome;}
}
