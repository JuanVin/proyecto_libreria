package com.prog.libreria.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categorias")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (length = 64, unique=false)
    private String nombre;
    @Column
    private Boolean activo = true;

    @OneToMany(mappedBy = "categoria")
    private List<Libro> libros;

}
