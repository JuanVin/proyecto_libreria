/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prog.libreria.service.imp;

import com.prog.libreria.commons.GenericServiceImpl;
import com.prog.libreria.service.PersonaService;
import com.prog.libreria.entities.Persona;
import com.prog.libreria.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan
 */
@Service
public class PersonaServiceImp extends GenericServiceImpl<Persona, Long> implements PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
            
    @Override
    public JpaRepository<Persona, Long> getRepository() {
        return personaRepository;
    }

    @Override
    public boolean deleteById(long id) throws Exception {
        return false;
    }
}
