package com.alurachallenge.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int anoNacimiento,
        @JsonAlias("death_year") int anoMuerte
) {
    public boolean vivoEnAno(int anoConsulta){
        return (anoConsulta >= anoNacimiento && anoConsulta <= anoMuerte);

    }

}
