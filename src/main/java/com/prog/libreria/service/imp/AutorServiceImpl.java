package com.prog.libreria.service.imp;

import com.prog.libreria.commons.GenericServiceImpl;
import com.prog.libreria.entities.Autor;
import com.prog.libreria.repository.AutorRepository;
import com.prog.libreria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AutorServiceImpl extends GenericServiceImpl<Autor, Long> implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
       try {
           Optional<Autor> opt = getRepository().findById(id);
           if(!opt.isEmpty()){
               Autor autor = opt.get();
               autor.setActivo(!autor.getActivo());
               getRepository().save(autor);
           }else{
               throw new Exception();
           }
           return true;
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }

    @Override
    public JpaRepository<Autor, Long> getRepository() {
        return autorRepository;
    }
}
