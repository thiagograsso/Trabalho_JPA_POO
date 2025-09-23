package com.streaming.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length=255)
    private String nome;

    @Column(nullable=false, unique=true, length=255)
    private String email;

    @Column(nullable=false, length=255)
    private String senha;

    @Column(name="data_cadastro", nullable=false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario() {}
    public Usuario(String nome, String email, String senha, LocalDateTime dataCadastro){
        this.nome=nome; this.email=email; this.senha=senha; this.dataCadastro=dataCadastro;
    }

    // getters e setters
    public Integer getId(){return id;}
    public String getNome(){return nome;}
    public String getEmail(){return email;}
    public List<Perfil> getPerfis(){return perfis;}
}
