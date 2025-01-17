package com.aluralatam.retoliteratura.literaturapersistente.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
   @JsonAlias("title") String titulo,
   @JsonAlias("id") Integer idLibro,
   @JsonAlias("download_count") Integer totalDescargas,
   @JsonAlias("languages") List<String> lenguajes,
   @JsonAlias("bookshelves") List<String> estanteria,
   @JsonAlias("authors") List<DatosAutores> autores
) {
}
