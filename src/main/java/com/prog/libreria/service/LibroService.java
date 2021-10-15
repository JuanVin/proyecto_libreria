package com.prog.libreria.service;

import com.prog.libreria.commons.GenericService;
import com.prog.libreria.entities.Libro;
import org.springframework.stereotype.Service;

@Service
public interface LibroService extends GenericService<Libro, Long> {
}
