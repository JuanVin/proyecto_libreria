package com.prog.libreria.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="autores")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (length = 64, unique=false)
    private String nombre;
    @Column
    private Boolean activo = true;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    public String upperCaseFirst(){
        String str = this.getNombre();
        String firstLtr = str.substring(0, 1);
        String restLtrs = str.substring(1, str.length());

        firstLtr = firstLtr.toUpperCase();
        str = firstLtr + restLtrs;
        return str;
    }
}
