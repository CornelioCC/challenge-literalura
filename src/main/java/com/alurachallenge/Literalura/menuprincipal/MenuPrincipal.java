package com.alurachallenge.Literalura.menuprincipal;

import aj.org.objectweb.asm.TypeReference;
import com.alurachallenge.Literalura.modelos.Autor;
import com.alurachallenge.Literalura.modelos.Idioma;
import com.alurachallenge.Literalura.modelos.Libro;
import com.alurachallenge.Literalura.modelos.RLibro;
import com.alurachallenge.Literalura.repositorio.LibroRepositorio;
import com.alurachallenge.Literalura.servicios.ConsumoApi;
import com.alurachallenge.Literalura.servicios.Convertidor;

import java.util.ArrayList;
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
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnDeterminadoAno();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    break;
            }

        }
        System.out.println("Programa finalizado");

    }

    private void listarLibrosPorIdioma() {
        boolean idiomaValido = false;
        List<Idioma> escogidoL = new ArrayList<>();
        while(!idiomaValido) {
            System.out.println("-----Idiomas disponibles-----");
            for (Idioma i : Idioma.values()) {
                System.out.println("\n" + i.toString() + " - " + i.getIdiomaEnPalabras());
            }
            System.out.println("-----------------------------");
            System.out.println("Escriba las dos letras correspondientes al idioma que desea buscar: ");
            String idioma = entrada.nextLine();
            Idioma escogido = Idioma.fromString(idioma);
            escogidoL.add(escogido);
            if (escogido != null){
                idiomaValido = true;
            }else{
                System.out.println("Idioma no valido, intente de nuevo");
            }
        }
        List<Libro> listaLibros = repo.findByIdiomas(escogidoL);
        if (listaLibros.size() > 0) {
            System.out.println("----- Libros Encontrados -----");
            System.out.println(listaLibros);
        }else{
            System.out.println("No se ha registrado ningun libro en ese idioma");
        }
    }

    private void listarAutoresVivosEnDeterminadoAno() {
        boolean numeroIngresado = false;
        int anoConsulta = 0;
        while (!numeroIngresado){
            System.out.println("Ingrese el año en que desea consultar: ");
            try{
                anoConsulta = Integer.valueOf(entrada.nextLine());
                numeroIngresado = true;
            }catch (Exception e){
                System.out.println("No se ha ingresado un numero entero");
            }

        }
        List<Autor> listaAutores = repo.listaAutores();
        for (Autor a : listaAutores){
            if (a.estaVivoEnAno(anoConsulta)){
                System.out.println(a);
            }
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("-----Lista de Autores-----");
        List<Autor> listaAutores = repo.listaAutores();
        listaAutores.stream()
                .forEach(System.out::println);
        System.out.println("--------------------------");
    }

    private void listarLibrosRegistrados() {
        System.out.println("-----Lista de libros-----");
        List<Libro> listaLibros = repo.findAll();
        if (listaLibros.size() == 0){
            System.out.println("Ningun libro registrado aun");
        }
        listaLibros.stream()
                .forEach(System.out::println);
        System.out.println("-------------------------");
    }

    private void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String nombreLibro = entrada.nextLine();
        var resultado = consumoApi.obtenerDatos(URL_BASE + BUSCAR + nombreLibro.replace(" ", "+").trim());
        Libro libroEncontrado = new Libro();
        try {
            RLibro libro = convertidor.obtenerRLibroDesdeJson(resultado);
            libroEncontrado = new Libro(libro);
            System.out.println(libroEncontrado);
        }catch (Exception e){
            System.out.println("No se ha encontrado ningun libro");
        }
        if (libroEncontrado != null) {
            try {
                repo.save(libroEncontrado);
                System.out.println("El libro se registro con exito");
            } catch (Exception e) {
                if (libroEncontrado.getTitulo() == null){
                    System.out.println("Libro no encontrado");
                }else if(libroEncontrado.getTitulo().length() > 255){
                    System.out.println("Titulo demasiado largo para registrar");
                }else{
                    System.out.println("Libro ya registrado");
                }

            }
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