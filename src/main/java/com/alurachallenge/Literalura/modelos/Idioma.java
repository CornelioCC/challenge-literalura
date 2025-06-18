package com.alurachallenge.Literalura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum Idioma {
    en,
    hu,
    es;


        public static Idioma fromString(String idioma){
            for(Idioma i : Idioma.values()){
                if (i.equals(idioma)){
                    return i;
                }
            }
            return null;
        }
    }
