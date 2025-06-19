package com.alurachallenge.Literalura.repositorio;

import com.alurachallenge.Literalura.modelos.Autor;
import com.alurachallenge.Literalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;


public interface LibroRepositorio extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
    @Query("SELECT a FROM Libro l JOIN l.autores a")
    List<Autor> listaAutores();



}
