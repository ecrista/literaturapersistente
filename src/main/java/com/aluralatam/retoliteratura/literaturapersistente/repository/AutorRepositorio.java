package com.aluralatam.retoliteratura.literaturapersistente.repository;

import com.aluralatam.retoliteratura.literaturapersistente.modelos.Autor;
import com.aluralatam.retoliteratura.literaturapersistente.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a JOIN a.libros")
    List<Autor> obtenerAutoresRegistrados();

    @Query("SELECT a FROM Autor a JOIN a.libros WHERE fechaNacimiento<=:fechaVida AND fechaMuerte>:fechaVida")
    List<Autor> obtenerAutoresPorFechas(Integer fechaVida);

    Optional<Autor> findBynombre(String nombre);

    @Query("SELECT l.lenguajes FROM Libro l WHERE l.lenguajes LIKE :lenguajes")
    List<Libro> buscarLibrosLenguajes(String lenguajes);

}
