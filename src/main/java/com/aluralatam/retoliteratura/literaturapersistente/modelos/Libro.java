package com.aluralatam.retoliteratura.literaturapersistente.modelos;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Integer idLibro;
    @Column(unique = true)
    private String nombre;
    private String lenguajes;
    private Integer descargas;
    @ManyToOne
    private Autor autores;

    public Libro(){

    }
    public Libro(DatosLibros datosLibros) {
        this.idLibro = datosLibros.idLibro();
        this.nombre = datosLibros.titulo();
        this.lenguajes = datosLibros.lenguajes().get(0);
        this.descargas = datosLibros.totalDescargas();
//        this.autores=datosLibros.autores().stream()
//                .map(a->new Autor(a.nombre(),a.fechaNacimiento(),a.fechaMuerte()))
//                .toList()
//                .get(0);
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", descargas=" + descargas;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        this.autores = autores;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

}
