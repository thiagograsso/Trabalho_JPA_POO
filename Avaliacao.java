package com.streaming.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="perfil_id", nullable=false)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="video_id", nullable=false)
    private Video video;

    @Column(nullable=false)
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    public Avaliacao(){}
    public Avaliacao(Perfil perfil, Video video, Integer nota, String comentario){
        this.perfil = perfil; this.video = video; this.nota = nota; this.comentario = comentario;
    }
}
