package com.prog.libreria.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan
 */
@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID>{

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getRepository().findById(id);
        if(obj.isPresent()) return obj.get();
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> returnList = new ArrayList<>();
        getRepository().findAll().forEach(obj -> returnList.add(obj));
        return returnList;
    }

    @Override
    public T update(T entity, ID id) throws Exception {
        try{
            Optional<T> opt = getRepository().findById(id);

            if(!opt.isEmpty()){
                T ent = opt.get();
                ent = entity;
                getRepository().save(ent);
            }else{
                throw new Exception();
            }
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public abstract JpaRepository<T, ID> getRepository();
}
