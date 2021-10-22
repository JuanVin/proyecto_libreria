package com.prog.libreria.service.imp;

import com.prog.libreria.commons.GenericServiceImpl;
import com.prog.libreria.entities.Libro;
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
    public boolean deleteById(Long id) throws Exception {
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

    @Override
    @Transactional
    public List<Libro> findAllByActivo() throws Exception{
        try{
            List<Libro> entities = libroRepository.findAllByActivo();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Libro> findAllByActivoAndCategory(Long id) throws Exception {
        try{
            List<Libro> entities = libroRepository.findAllByCategoryAndActivo(id);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Libro findByIdAndActivo(Long id) throws Exception{
        try{
            Optional<Libro> entity= libroRepository.findByIDAndActivo(id);

            return entity.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JpaRepository<Libro, Long> getRepository() {
        return libroRepository;
    }

}
