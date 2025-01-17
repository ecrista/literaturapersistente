package com.aluralatam.retoliteratura.literaturapersistente.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosListaLibros(
        @JsonAlias("results") List<DatosLibros> libros
) {
}
