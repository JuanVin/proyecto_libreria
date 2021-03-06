package com.prog.libreria.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private long id;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lanzamiento;
    @Column
    private Boolean activo = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_autor", nullable = false)
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_categoria", nullable = false)
    private Categoria categoria;

    public String upperCaseFirst(){
        String str = this.getTitulo();
        String firstLtr = str.substring(0, 1);
        String restLtrs = str.substring(1, str.length());

        firstLtr = firstLtr.toUpperCase();
        str = firstLtr + restLtrs;
        return str;
    }
    @Transient
    public String getPhotosImagePath() {
        if (rutaImg == null || id == 0) return null;

        return "/book-photos/" + id + "/" + rutaImg;
    }
}
