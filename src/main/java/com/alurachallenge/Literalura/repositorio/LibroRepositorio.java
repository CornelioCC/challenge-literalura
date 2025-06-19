package com.alurachallenge.Literalura.repositorio;

import com.alurachallenge.Literalura.modelos.Autor;
import com.alurachallenge.Literalura.modelos.Idioma;
import com.alurachallenge.Literalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;


public interface LibroRepositorio extends JpaRepository<Libro,Long> {

    @Query("SELECT a FROM Libro l JOIN l.autores a")
    List<Autor> listaAutores();

    List<Libro> findByIdiomas(List<Idioma> i);




}
