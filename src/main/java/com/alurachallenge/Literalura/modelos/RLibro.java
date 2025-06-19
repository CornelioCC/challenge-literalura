package com.alurachallenge.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RLibro(
        @JsonAlias("id") long id,
        @JsonAlias("title") String titulo,
        List<RAutor> autores,
        List<Idioma> idiomas,
        @JsonAlias("download_count") long numeroDescargas)

{
}
