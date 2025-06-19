package com.alurachallenge.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum Idioma {
    en ("Ingles"),
    fr ("Frances"),
    pt ("Portugues"),
    es ("Español");
    private String palabra;

    Idioma(String palabra) {
        this.palabra = palabra;
    }
    public String getIdiomaEnPalabras(){
        return palabra;
    }

    public static Idioma fromString(String idioma){
            for(Idioma i : Idioma.values()){
                System.out.println( idioma + "    "  + i);
                if (idioma.equalsIgnoreCase(i.toString())){
                    return i;
                }
            }
            return null;
        }
    }
