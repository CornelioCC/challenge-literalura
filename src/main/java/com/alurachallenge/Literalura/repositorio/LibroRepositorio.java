package com.alurachallenge.Literalura.repositorio;

import com.alurachallenge.Literalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);


}
