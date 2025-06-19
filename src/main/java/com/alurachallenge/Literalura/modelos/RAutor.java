package com.alurachallenge.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int anoNacimiento,
        @JsonAlias("death_year") int anoMuerte
) {

}
