package com.streaming.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo"}))
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length=255, unique=true)
    private String titulo;

    @Column(length=1000)
    private String descricao;

    @Column(nullable=false)
    private Integer duracao; // em segundos ou minutos, conforme sua escolha

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoria_id", nullable=false)
    private Categoria categoria;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visualizacao> visualizacoes = new ArrayList<>();

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Video(){}
    public Video(String titulo, String descricao, Integer duracao, Categoria categoria){
        this.titulo = titulo; this.descricao = descricao; this.duracao = duracao; this.categoria = categoria;
    }

    public Integer getId(){return id;}
    public String getTitulo(){return titulo;}
}
