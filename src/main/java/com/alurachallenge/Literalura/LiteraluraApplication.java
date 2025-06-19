package com.alurachallenge.Literalura;

import com.alurachallenge.Literalura.menuprincipal.MenuPrincipal;
import com.alurachallenge.Literalura.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal menu = new MenuPrincipal(repositorio);
		menu.muestraMenu();
	}
}
