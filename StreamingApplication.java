package com.streaming;

import com.streaming.entity.*;
import com.streaming.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class StreamingApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamingApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UsuarioRepository usuarioRepo,
                             PerfilRepository perfilRepo,
                             CategoriaRepository categoriaRepo,
                             VideoRepository videoRepo,
                             VisualizacaoRepository visRepo,
                             AvaliacaoRepository avalRepo) {
        return args -> {
            // --- Criar usuários e perfis
            Usuario u1 = new Usuario("João", "joao@ex.com", "senha", LocalDateTime.now());
            Usuario u2 = new Usuario("Maria", "maria@ex.com", "senha2", LocalDateTime.now());
            usuarioRepo.save(u1);
            usuarioRepo.save(u2);

            Perfil p1 = new Perfil("Joao_Principal", u1);
            Perfil p2 = new Perfil("Maria_Principal", u2);
            Perfil p3 = new Perfil("Joao_Secundario", u1);
            perfilRepo.save(p1); perfilRepo.save(p2); perfilRepo.save(p3);

            // --- Categorias
            Categoria cAventura = new Categoria("Aventura");
            Categoria cComedia = new Categoria("Comédia");
            Categoria cMissao = new Categoria("Missão");
            categoriaRepo.save(cAventura); categoriaRepo.save(cComedia); categoriaRepo.save(cMissao);

            // --- Videos
            Video v1 = new Video("Missão Impossível", "Ação e espionagem", 120, cMissao);
            Video v2 = new Video("Missão Secreta", "Thriller", 95, cMissao);
            Video v3 = new Video("Comédia Leve", "Risadas", 80, cComedia);
            Video v4 = new Video("Aventura no Mar", "Aventura", 110, cAventura);
            Video v5 = new Video("Missão Final", "Final épico", 130, cMissao);
            videoRepo.saveAll(List.of(v1,v2,v3,v4,v5));

            // --- Visualizações (muitos para criar contagem)
            visRepo.save(new Visualizacao(p1, v1, LocalDateTime.now().minusDays(2), 100));
            visRepo.save(new Visualizacao(p1, v1, LocalDateTime.now().minusDays(1), 100));
            visRepo.save(new Visualizacao(p2, v1, LocalDateTime.now().minusDays(5), 50));
            visRepo.save(new Visualizacao(p1, v2, LocalDateTime.now().minusDays(2), 100));
            visRepo.save(new Visualizacao(p3, v1, LocalDateTime.now().minusDays(3), 20));
            visRepo.save(new Visualizacao(p2, v3, LocalDateTime.now().minusDays(4), 100));
            visRepo.save(new Visualizacao(p1, v5, LocalDateTime.now().minusHours(4), 100));
            visRepo.save(new Visualizacao(p2, v5, LocalDateTime.now().minusHours(10), 100));
            visRepo.save(new Visualizacao(p3, v5, LocalDateTime.now().minusHours(8), 80));
            visRepo.save(new Visualizacao(p1, v4, LocalDateTime.now().minusDays(7), 50));
            // adiciona mais para influência na ordenação
            for (int i=0;i<20;i++){
                visRepo.save(new Visualizacao(p1, v3, LocalDateTime.now().minusDays(i), 100));
            }

            // --- Avaliações
            avalRepo.save(new Avaliacao(p1, v1, 5, "Ótimo!"));
            avalRepo.save(new Avaliacao(p2, v1, 4, "Bom"));
            avalRepo.save(new Avaliacao(p1, v5, 5, "Excelente"));
            avalRepo.save(new Avaliacao(p3, v5, 4, "Bom final"));
            avalRepo.save(new Avaliacao(p2, v3, 3, "Ok"));
            avalRepo.save(new Avaliacao(p1, v4, 4, "Legal"));

            // --- Execução das consultas pedidas:

            System.out.println("=== Buscar vídeos pelo título com ordenação (ex: 'Missão') ===");
            List<Video> buscaTitulo = videoRepo.findByTituloContainingIgnoreCaseOrderByTituloAsc("Missão");
            buscaTitulo.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n=== Todos os vídeos de uma categoria ordenado pelo título (Categoria: Missão) ===");
            List<Video> cat = videoRepo.findByCategoriaOrderByTituloAsc(cMissao);
            cat.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n=== Top 10 vídeos mais bem avaliados ===");
            List<Video> topRated = videoRepo.findTopRated(PageRequest.of(0,10));
            topRated.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n=== Top 10 vídeos mais assistidos ===");
            List<Video> topViewed = videoRepo.findTopViewed(PageRequest.of(0,10));
            topViewed.forEach(v -> System.out.println(v.getTitulo()));

            System.out.println("\n=== Usuário que mais assistiu vídeos ===");
            List<Usuario> topUsers = usuarioRepo.findTopWatchingUsers(PageRequest.of(0,1));
            topUsers.forEach(u -> System.out.println(u.getNome() + " - id: " + u.getId()));

        };
    }
}
