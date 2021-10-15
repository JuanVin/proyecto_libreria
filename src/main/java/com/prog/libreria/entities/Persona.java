/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.libreria.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author juan
 */
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (length = 64, unique=false)
    private String nombre;
    @Column (length = 64, unique=false)
    private String apellido;
    @Column (length = 64, unique=false)
    private String direccion;
    @Column (length = 64, unique=false)
    private String telefono;

}
