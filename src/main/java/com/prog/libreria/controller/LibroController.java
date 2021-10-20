package com.prog.libreria.controller;

import com.prog.libreria.entities.Autor;
import com.prog.libreria.entities.Categoria;
import com.prog.libreria.entities.Libro;
import com.prog.libreria.service.LibroService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller

public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index() throws Exception {
        Autor autor = Autor.builder().nombre("El pepe").build();
        Categoria categoria = Categoria.builder().activo(true).nombre("Fantasía").build();
        Categoria categoria2 = Categoria.builder().activo(true).nombre("Horror").build();
        Categoria categoria3 = Categoria.builder().activo(true).nombre("Comics").build();
        Libro libro = Libro.builder().activo(true).autor(autor).categoria(categoria).titulo("World of Warcraft: Crónicas").rutaImg("https://images-na.ssl-images-amazon.com/images/I/A1a-wgltdoL.jpg").descripcion("Desde sus orígenes, el mundo de Azeroth existe gracias a la colaboración de cientos de técnicos, diseñadores, artistas y guionistas. Este libro –esta Crónica– pretende aunar todos estos elementos y reforzar la narrativa global que yace en el corazón de Warcraft.").precio(125.5).build();
        Libro libro2 = Libro.builder().activo(true).autor(autor).categoria(categoria).titulo("World of Warcraft: Cronicas 3").rutaImg("https://images-na.ssl-images-amazon.com/images/I/A13msEm8XwL.jpg").descripcion("Llega la tercera entrega de la mundialmente famosa World of Warcraft: Crónicas. Este volumen revela detalles más específicos sobre la historia y la mitología del universo del juego. ").precio(130.8).build();
        Libro libro3 = Libro.builder().activo(true).autor(autor).categoria(categoria2).titulo("Call of Cthulhu: The Coloring Book").rutaImg("https://1.bp.blogspot.com/-vnhusqNdAcE/WJzojGjjIOI/AAAAAAAAqyM/jW7ZvRgHwgEgWPoAj18mtSsSN_MN7mQhACLcB/s1600/cha5116_-_call_of_cthulhu_coloring_book_1107.JPG").descripcion("Llega la segunda entrega de este apasionante viaje al rico y complejo mundo de Warcraft, un universo de mitos, leyendas y geografías fantásticas").precio(150.).build();
        Libro libro4 = Libro.builder().activo(true).autor(autor).categoria(categoria2).titulo("H. P. Lovecraft: Vida y obra ilustrada").rutaImg("https://cdn.normacomics.com/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/l/o/lovecraft_vida_ilustrada.jpg").descripcion("Novela de horror").precio(180.).build();
        Libro libro5 = Libro.builder().activo(true).autor(autor).categoria(categoria).titulo("World of Warcraft: Cronicas 2").rutaImg("https://m.media-amazon.com/images/I/610YrvrEL2L.jpg").descripcion("Novela de horror").precio(200.).build();
        Libro libro6 = Libro.builder().activo(true).autor(autor).categoria(categoria3).titulo("Amazin Fantacy: Spider Man").rutaImg("https://pm1.narvii.com/6336/9acd1cdaa66dc16a4ba5580c83ddde391a9f3719_hq.jpg").descripcion("Amazin Fantacy: Spider Man").build();
        Libro libro7 = Libro.builder().activo(true).autor(autor).categoria(categoria3).titulo("Adam Kayser: Los inmortales").rutaImg("https://cdn.domestika.org/c_limit,dpr_auto,f_auto,q_auto,w_820/v1571347784/content-items/003/345/636/AK01_ins-original.jpg?1571347784").descripcion("Adam Kayser: Los inmortales").build();
        Libro libro8 = Libro.builder().activo(true).autor(autor).categoria(categoria3).titulo("John Wick").rutaImg("https://cl.buscafs.com/www.tomatazos.com/public/uploads/images/150462/150462_600x900.jpg").descripcion("Novela de horror").build();




        autor.setLibros(Arrays.asList(libro));
        categoria.setLibros(Arrays.asList(libro));

        libroService.save(libro);
        libroService.save(libro2);
        libroService.save(libro3);
        libroService.save(libro4);
        libroService.save(libro5);
        libroService.save(libro6);
        libroService.save(libro7);
        libroService.save(libro8);
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogue(Model model) throws Exception {
        List<Libro> libros = libroService.findAllByActivo();
        List<Libro> fantasia = new ArrayList<Libro>();
        List<Libro> horror = new ArrayList<Libro>();
        List<Libro> comic = new ArrayList<Libro>();
        libros.forEach(libro -> {
            if(libro.getCategoria().getNombre().toLowerCase(Locale.ROOT).equals("fantasía") && fantasia.size()<4){
                fantasia.add(libro);
            }
            if(libro.getCategoria().getNombre().toLowerCase(Locale.ROOT).equals("horror") && horror.size()<4){
                horror.add(libro);
            }
            if(libro.getCategoria().getNombre().toLowerCase(Locale.ROOT).equals("comics") && comic.size()<4){
                comic.add(libro);
            }
        });

        model.addAttribute("fantasia", fantasia);
        model.addAttribute("horror", horror);
        model.addAttribute("comic", comic);
        return "catalogue";
    }

    @GetMapping(value = "/catalogo/buy/{id}")
    public String bookDetails(Model model, @PathVariable("id") long id) {

        try {
            Libro libro = libroService.findByIdAndActivo(id);
            model.addAttribute("libro", libro);
            return "buy-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }




}
