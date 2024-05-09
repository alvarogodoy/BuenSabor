package com.example.buensaboruno.repositories;

import com.example.buensaboruno.domain.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long> {
}
