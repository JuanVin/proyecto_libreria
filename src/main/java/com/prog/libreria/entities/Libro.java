package com.prog.libreria.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="libros")
@Builder
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
    private Double precio;
    @Column
    private Short cantidad;
    @Column(length = 500)
    private String descripcion;
    @Column
    private Boolean oferta;
    @Column
    private Date lanzamiento;
    @Column
    private Boolean activo = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_autor", nullable = false)
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_categoria", nullable = false)
    private Categoria categoria;
}
