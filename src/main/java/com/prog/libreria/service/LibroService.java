package com.prog.libreria.service;

import com.prog.libreria.commons.GenericService;
import com.prog.libreria.entities.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibroService extends GenericService<Libro, Long> {
    public Libro findByIdAndActivo(Long id) throws Exception;
    public List<Libro> findAllByActivo() throws Exception;
    public List<Libro> findAllByActivoAndCategory(Long id) throws  Exception;
}
