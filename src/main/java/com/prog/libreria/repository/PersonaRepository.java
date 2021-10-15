/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.libreria.repository;

import com.prog.libreria.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author juan
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
