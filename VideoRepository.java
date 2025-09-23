package com.streaming.repository;

import com.streaming.entity.Categoria;
import com.streaming.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    // Buscar vídeos pelo título com ordenação (contém, case-insensitive)
    List<Video> findByTituloContainingIgnoreCaseOrderByTituloAsc(String tituloPart);

    // Todos os vídeos de uma categoria ordenado pelo título
    List<Video> findByCategoriaOrderByTituloAsc(Categoria categoria);

    // Top 10 vídeos mais bem avaliados (por média de notas)
    @Query("SELECT v FROM Video v LEFT JOIN v.avaliacoes a GROUP BY v.id ORDER BY COALESCE(AVG(a.nota), 0) DESC")
    List<Video> findTopRated(Pageable pageable);

    // Top 10 vídeos mais assistidos (por contagem de visualizacoes)
    @Query("SELECT v FROM Video v LEFT JOIN v.visualizacoes vis GROUP BY v.id ORDER BY COUNT(vis) DESC")
    List<Video> findTopViewed(Pageable pageable);
}
