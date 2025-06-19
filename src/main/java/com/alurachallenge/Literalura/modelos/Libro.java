package com.alurachallenge.Literalura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    @Enumerated(EnumType.STRING)
    private List<Idioma> idiomas;
    private long numeroDescargas;

    public Libro() {
    }


    public Libro(RLibro record) {
        this.id = record.id();
        this.titulo = record.titulo();
        this.autores = Autor.desdeRecord(record.autores());
        Autor.agregarLibroAAutores(this.autores, this);
        this.idiomas = record.idiomas();
        this.numeroDescargas = record.numeroDescargas();
    }

    @Override
    public String toString(){
        String cadenaAutores = "";
        for(Autor a : this.autores){
            cadenaAutores += a.getNombre() + " , ";
        }
        String cadenaIdiomas = "";
        for(Idioma i : this.idiomas){
            cadenaIdiomas += i.toString();
        }
        return "\n----- LIBRO -----\n" +
                "Titulo : " + this.titulo +
                "\nAutores : " + cadenaAutores.substring(0, cadenaAutores.length() - 3) +
                "\nIdiomas : " + cadenaIdiomas +
                "\nNumero de descargas : " + this.numeroDescargas +
                "\n------------------";


    }

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

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
