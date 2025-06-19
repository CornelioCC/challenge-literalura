package com.alurachallenge.Literalura.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private int anoNacimiento;
    private int anoMuerte;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    @Override
    public String toString(){
        String cadenaLibros = "";
        for (Libro l : this.libros){
            cadenaLibros += l.getTitulo() + " , ";
        }
        return "\n----- Autor -----\n" +
                "Nombre : " + this.nombre +
                "\nAño de Nacimiento: " + this.anoNacimiento +
                "\nAño de Fallecimiento: " + this.anoMuerte +
                "\nLibros : " + cadenaLibros.substring(0,cadenaLibros.length() - 3) +
                "--------------------";
    }

    public boolean estaVivoEnAno(int anoConsulta){
        return (anoConsulta >= anoNacimiento && anoConsulta <= anoMuerte);
    }

    public void agregarLibro(Libro libro){
        this.libros.add(libro);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public static List<Autor> desdeRecord(List<RAutor> lista){
        List<Autor> nuevaLista = new ArrayList<>();
        for(RAutor a : lista){
            nuevaLista.add(new Autor(a));
        }
        return nuevaLista;
    }

    public static void agregarLibroAAutores(List<Autor> lista, Libro libro){
        for (Autor a : lista){
            a.agregarLibro(libro);
        }
    }


    public Autor(String nombre, int anoNacimiento, int anoMuerte) {
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoMuerte = anoMuerte;
    }
    public Autor(){

    }
    public Autor(RAutor autor){
        this.nombre = autor.nombre();
        this.anoMuerte = autor.anoMuerte();
        this.anoNacimiento = autor.anoNacimiento();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getAnoMuerte() {
        return anoMuerte;
    }

    public void setAnoMuerte(int anoMuerte) {
        this.anoMuerte = anoMuerte;
    }
}
