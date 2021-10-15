package com.prog.libreria.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="libros")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String rutaImg;
    @Column
    private Short cantidad;
    @Column
    private String descripcion;
    @Column
    private Boolean oferta;
    @Column
    private Date lanzamiento;
    @Column
    private Boolean activo = true;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="fk_autor", nullable = false)
    private Autor autor;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="fk_categoria", nullable = false)
    private Categoria categoria;
}
