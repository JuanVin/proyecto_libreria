package com.prog.libreria.repository;

import com.prog.libreria.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value="SELECT * FROM categorias where categorias.nombre= :category", nativeQuery = true)
    Optional<Categoria> findByName(@Param("category") String categoria);
}
