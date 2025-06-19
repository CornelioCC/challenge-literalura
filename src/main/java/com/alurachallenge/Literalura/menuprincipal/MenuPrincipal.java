package com.alurachallenge.Literalura.menuprincipal;

import aj.org.objectweb.asm.TypeReference;
import com.alurachallenge.Literalura.modelos.Libro;
import com.alurachallenge.Literalura.modelos.RLibro;
import com.alurachallenge.Literalura.repositorio.LibroRepositorio;
import com.alurachallenge.Literalura.servicios.ConsumoApi;
import com.alurachallenge.Literalura.servicios.Convertidor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private Convertidor convertidor = new Convertidor();
    private Scanner entrada = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/?";
    private final String BUSCAR = "search=";
    private LibroRepositorio repo;
    private Optional<Libro> libroBuscado;

    public MenuPrincipal(LibroRepositorio repositorio) {
        this.repo = repositorio;
    }

    public void muestraMenu() {

        int opcion = -1;
        while(opcion != 0) {
            opcion = seleccionOpcion();
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    break;
            }

        }
        System.out.println("Programa finalizado");

    }

    private void listarLibrosRegistrados() {
        System.out.println("-----Lista de libros-----");
        List<Libro> listaLibros = repo.findAll();
        if (listaLibros.size() == 0){
            System.out.println("Ningun libro registrado aun");
        }
        listaLibros.stream()
                .forEach(System.out::println);
    }

    private void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = entrada.nextLine();
        var resultado = consumoApi.obtenerDatos(URL_BASE + BUSCAR + nombreLibro.replace(" ", "+").trim());
        //var resultado = consumoApi.obtenerDatos(URL_BASE + "ids=51238");
        RLibro libro = convertidor.obtenerRLibroDesdeJson(resultado);
        Libro libroEncontrado = new Libro(libro);
        System.out.println(libroEncontrado);
        try{
            repo.save(libroEncontrado);
            System.out.println("El libro se registro con exito");
        }catch (Exception e){
            System.out.println("Libro ya registrado");
        }
    }


    private int seleccionOpcion() {
        int opcion = -1;
        boolean opcionValida = false;
        while (!opcionValida) {
            System.out.println("""
                    Menu de opciones: 
                    
                    1.- Buscar libro por titulo
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0.- Salir
                    
                    Ingrese el numero de opcion que quiera utilizar:
                    """);

            try {
                opcion = Integer.valueOf(entrada.nextLine());
                if (opcion < 0 || opcion > 5) {
                    System.out.println("Opcion no disponible");
                }else {
                    opcionValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("No se ha ingresado un numero entero");
            }
        }
        return opcion;
    }
}