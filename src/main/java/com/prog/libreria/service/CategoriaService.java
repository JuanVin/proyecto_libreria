package com.prog.libreria.service;

import com.prog.libreria.commons.GenericService;
import com.prog.libreria.entities.Categoria;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService extends GenericService<Categoria, Long> {
   Categoria findByName(String categoria) throws Exception;
}
