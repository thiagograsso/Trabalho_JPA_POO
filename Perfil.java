package com.streaming.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfil",
       uniqueConstraints = @UniqueConstraint(columnNames = {"nome_perfil"}))
public class Perfil {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome_perfil", nullable=false, length=255)
    private String nomePerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visualizacao> visualizacoes = new ArrayList<>();

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Perfil(){}
    public Perfil(String nomePerfil, Usuario usuario){
        this.nomePerfil = nomePerfil; this.usuario = usuario;
    }

    // getters
    public Integer getId(){return id;}
    public String getNomePerfil(){return nomePerfil;}
    public Usuario getUsuario(){return usuario;}
}
