package com.streaming.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visualizacao")
public class Visualizacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="perfil_id", nullable=false)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="video_id", nullable=false)
    private Video video;

    @Column(name="data_hora", nullable=false)
    private LocalDateTime dataHora;

    @Column(nullable=false)
    private Integer progresso;

    public Visualizacao(){}
    public Visualizacao(Perfil perfil, Video video, LocalDateTime dataHora, Integer progresso){
        this.perfil = perfil; this.video = video; this.dataHora = dataHora; this.progresso = progresso;
    }

    public Integer getId(){return id;}
}
