package com.streaming.repository;

import com.streaming.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // O usuário que mais assistiu vídeos (considerando todas as visualizações dos perfis)
    @Query("SELECT u FROM Usuario u JOIN u.perfis p JOIN p.visualizacoes v GROUP BY u.id ORDER BY COUNT(v) DESC")
    List<Usuario> findTopWatchingUsers(Pageable pageable);
}
