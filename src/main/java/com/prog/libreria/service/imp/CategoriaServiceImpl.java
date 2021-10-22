package com.prog.libreria.service.imp;

import com.prog.libreria.commons.GenericServiceImpl;
import com.prog.libreria.entities.Categoria;
import com.prog.libreria.entities.Libro;
import com.prog.libreria.repository.CategoriaRepository;
import com.prog.libreria.service.CategoriaService;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Long> implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean deleteById(Long id) throws Exception {
        try {
            Optional<Categoria> opt = categoriaRepository.findById(id);
            if(!opt.isEmpty()){
                Categoria categoria = opt.get();
                categoria.setActivo(!categoria.getActivo());
                getRepository().save(categoria);
            }else{
                throw new Exception();
            }
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public JpaRepository<Categoria, Long> getRepository() {
        return categoriaRepository;
    }

    @Override
    public Categoria findByName(String categoria) throws Exception {
        try{
            Optional<Categoria> entity = categoriaRepository.findByName(categoria);
            return entity.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
