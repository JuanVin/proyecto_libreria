package com.prog.libreria.service.imp;

import com.prog.libreria.commons.GenericServiceImpl;
import com.prog.libreria.entities.Autor;
import com.prog.libreria.entities.Libro;
import com.prog.libreria.repository.AutorRepository;
import com.prog.libreria.repository.LibroRepository;
import com.prog.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl extends GenericServiceImpl<Libro, Long> implements LibroService {


    @Autowired
    private LibroRepository libroRepository;

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Libro> opt = libroRepository.findById(id);
            if(!opt.isEmpty()){
                Libro libro = opt.get();
                libro.setActivo(!libro.getActivo());
                getRepository().save(libro);
            }else{
                throw new Exception();
            }
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Libro> findAllByActivo() throws Exception{
        try{
            List<Libro> entities = libroRepository.findAllByActivo();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Libro> findByIdAndActivo(long id) throws Exception{
        try{
            List<Libro> entities = libroRepository.findByIDAndActivo(id);
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JpaRepository<Libro, Long> getRepository() {
        return libroRepository;
    }

}
