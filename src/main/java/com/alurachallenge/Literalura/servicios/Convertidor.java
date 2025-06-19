package com.alurachallenge.Literalura.servicios;

import com.alurachallenge.Literalura.modelos.Autor;
import com.alurachallenge.Literalura.modelos.RAutor;
import com.alurachallenge.Literalura.modelos.Idioma;
import com.alurachallenge.Literalura.modelos.RLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Convertidor {
    private ObjectMapper objectMapper = new ObjectMapper();

    public RLibro obtenerRLibroDesdeJson(String json) {

        try {
            //Obtencion del Json results con todos los libros
            JsonNode nodoInterno = objectMapper.readTree(json);
            String jsonLibros = nodoInterno.get("results").toString();

            //Partir el Json con todos los libros para apartar solo el primero
            JsonNode nodoLibros = objectMapper.readTree(jsonLibros);
            String primerLibro = nodoLibros.get(0).toString();

            //Mapear los nodos que contienen otro Json, autores e idiomas
            JsonNode nodosLejanos = objectMapper.readTree(primerLibro);

            //Json Autores
            String nodoAutores = nodosLejanos.get("authors").toString();
            String autoresJson = "[" + nodoAutores.substring(1, nodoAutores.length() - 1) + "]";
            JsonNode jsonAutores = objectMapper.readTree(autoresJson);
            //Crear la lista de autores
            List<RAutor> autores = new ArrayList<>();
            for (int i = 0; i < jsonAutores.size(); i++){
                autores.add(obtenerDatos(jsonAutores.get(i).toString(), RAutor.class));
            }

            //Json Idiomas
            String nodoIdioma = nodosLejanos.get("languages").toString();
            String idiomasJson = "["+ nodoIdioma.substring(1, nodoIdioma.length() - 1) +"]";

            JsonNode jsonIdiomas = objectMapper.readTree(idiomasJson);
            //Crear la lista de idiomas
            List<Idioma> idiomas = new ArrayList<>();
            for (int i = 0; i < jsonIdiomas.size(); i++){
                idiomas.add(obtenerDatos(jsonIdiomas.get(i).toString(), Idioma.class));
            }

            //Libros totales obtenidos en la busqueda
            List<RLibro> libros = objectMapper.readValue(jsonLibros, new TypeReference<List<RLibro>>(){});

            //RLibro completo con toda la informacion obtenida
            RLibro libroIncompleto = libros.get(0);

            RLibro librocompleto = new RLibro(libroIncompleto.id(), libroIncompleto.titulo(), autores, idiomas, libroIncompleto.numeroDescargas());

            return librocompleto;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
