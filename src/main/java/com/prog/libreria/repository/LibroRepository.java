package com.prog.libreria.repository;

import com.prog.libreria.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query(value="SELECT * FROM libros where libros.activo=true", nativeQuery = true)
    List<Libro> findAllByActivo();

    @Query(value="SELECT * FROM libros where libros.id= :id AND libros.activo=true", nativeQuery = true)
    List<Libro> findByIDAndActivo(@Param("id") long id);
}
