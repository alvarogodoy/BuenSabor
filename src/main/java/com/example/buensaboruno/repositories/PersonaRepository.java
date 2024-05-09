package com.example.buensaboruno.repositories;

import com.example.buensaboruno.domain.entities.Base;
import com.example.buensaboruno.domain.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface PersonaRepository extends BaseRepository<Persona,Long> {
}
