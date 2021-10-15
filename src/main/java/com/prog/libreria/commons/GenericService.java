/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.libreria.commons;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author juan
 */
public interface GenericService <T, ID extends Serializable> {
    T save (T entity) throws Exception;
    
    void delete (ID id) throws Exception;
    
    T get(ID id) throws Exception;
    
    List <T> getAll() throws Exception;

    T update (T entity, ID id) throws Exception;

    boolean deleteById (long id) throws Exception;
}
