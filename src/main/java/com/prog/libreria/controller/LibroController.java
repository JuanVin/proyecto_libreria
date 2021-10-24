package com.prog.libreria.controller;

import com.prog.libreria.config.FileUploadUtil;
import com.prog.libreria.entities.Categoria;
import com.prog.libreria.entities.Libro;
import com.prog.libreria.service.AutorService;
import com.prog.libreria.service.CategoriaService;
import com.prog.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller

public class LibroController {
    @Autowired
    private LibroService libroService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AutorService autorService;

    @GetMapping("/")
    public String index() throws Exception {
/*
        Autor autor = Autor.builder().nombre("J. R. R. Tolkien").build();
        Autor autor2 = Autor.builder().nombre("H. P. Lovecraft").build();
        Autor autor3 = Autor.builder().nombre("Ryan Stegman").build();
        Categoria categoria = Categoria.builder().activo(true).nombre("fantasia").build();
        Categoria categoria2 = Categoria.builder().activo(true).nombre("horror").build();
        Categoria categoria3 = Categoria.builder().activo(true).nombre("comics").build();

        autorService.save(autor);
        autorService.save(autor2);
        autorService.save(autor3);
        categoriaService.save(categoria);
        categoriaService.save(categoria2);
        categoriaService.save(categoria3);*/
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogue(Model model) throws Exception {
        List<Libro> libros = libroService.findAllByActivo();
        List<Libro> fantasia = new ArrayList<Libro>();
        List<Libro> horror = new ArrayList<Libro>();
        List<Libro> comic = new ArrayList<Libro>();

        libros.forEach(libro -> {
            if(libro.getCategoria().getNombre().toLowerCase(Locale.ROOT).equals("fantasia") && fantasia.size()<4){
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
    @GetMapping("catalogo/{category}")
    public String genericCatalogue(Model model, @PathVariable("category") String category) throws Exception {
        Categoria categoria = categoriaService.findByName(category);
        try{
            List<Libro> libros = libroService.findAllByActivoAndCategory(categoria.getId());
            model.addAttribute("libros", libros);
            model.addAttribute("categoria", categoria.upperCaseFirst());
            return "catalogue-list";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping("/form/book/{id}")
    public String formBook(Model model, @PathVariable("id") Long id) throws Exception {
        try{
            model.addAttribute("categorias", categoriaService.getAll());
            model.addAttribute("autores", autorService.getAll());
            if(id == 0){
                model.addAttribute("libro", new Libro());
                model.addAttribute("accion", "Agregar libro");
            }else{
                model.addAttribute("libro", libroService.get(id));
                model.addAttribute("accion", "Actualizar libro");
            }
            return "crud";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @PostMapping("/form/book/{id}")
    public String saveUpdateBook(@ModelAttribute("libro") Libro libro,
                                 @RequestParam("archivo") MultipartFile multipartFile,
                                 Model model, @PathVariable("id") Long id) throws Exception {
        try{

            String imageName ="";
            Libro savedBook;
            if(!multipartFile.isEmpty()){
                //fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                int index = multipartFile.getOriginalFilename().indexOf(".");
                String extension = "." + multipartFile.getOriginalFilename().substring(index + 1);
                imageName = Calendar.getInstance().getTimeInMillis() + extension;
                libro.setRutaImg(imageName);
            }
            System.out.println(libro.getPrecio() + libro.getTitulo());
            if(id == 0){
                savedBook = libroService.save(libro);
            }else{
                savedBook = libroService.update(libro, id);
            }
            if(!multipartFile.isEmpty()){
                String uploadDir = "book-photos/" + savedBook.getId();
                FileUploadUtil.saveFile(uploadDir, imageName, multipartFile);
            }

            return "redirect:/";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping("/book/list")
    public String bookActions(Model model) throws Exception {
        try{
            List<Libro> libros = libroService.getAll();
            model.addAttribute("libros", libros);
            model.addAttribute("categoria", "Libros/Comics");
            return "catalogue-list";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @GetMapping("/delete/book/{id}")
    public String deleteBook(Model model, @PathVariable("id") Long id) throws Exception {
        try{
            Libro libro = libroService.get(id);
            libro.setActivo(!libro.getActivo());
            libroService.save(libro);
            return "redirect:/book/list";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
