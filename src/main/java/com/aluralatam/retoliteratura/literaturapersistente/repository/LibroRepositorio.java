package com.aluralatam.retoliteratura.literaturapersistente.repository;

import com.aluralatam.retoliteratura.literaturapersistente.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro,Integer> {
    Optional<Libro> findBynombre(String nombre);

    @Query("SELECT l.lenguajes FROM Libro l GROUP BY l.lenguajes")
    List<String> buscarLenguajes();
    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE l.lenguajes = :lenguajes")
    List<Libro> buscarLibrosLenguajes(String lenguajes);

    List<Libro> findTop10ByOrderByDescargasDesc();

}
