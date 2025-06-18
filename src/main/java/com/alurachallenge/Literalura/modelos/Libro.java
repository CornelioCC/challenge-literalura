package com.alurachallenge.Literalura.modelos;

import java.util.List;

public class Libro {
    private long id;
    private String titulo;
    private List<Autor> autores;
    private Idioma idioma;
    private long numeroDescargas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
