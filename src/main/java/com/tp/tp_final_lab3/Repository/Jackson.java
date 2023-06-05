package com.tp.tp_final_lab3.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.tp.tp_final_lab3.Models.Dolarsito;
import com.tp.tp_final_lab3.Models.Usuario;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Jackson {

    public static <T> void serializar(T objeto,String pathJson)
    {
        File file = new File(pathJson);
        ObjectMapper mapper = new ObjectMapper();

        try{

            mapper.writerWithDefaultPrettyPrinter().writeValue(file,objeto);

        }catch (Exception e)
        {
            System.out.println("entro el catch");
            e.printStackTrace();
        }
    }

    public static <T> ArrayList<T> deserializarArrayList(String pathJson){

        File file = new File(pathJson);

        ObjectMapper mapper = new ObjectMapper();

        ArrayList<T> objetos = null;

        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Usuario.class);
            objetos = mapper.readValue(file, collectionType);

        } catch (IOException e){

           e.printStackTrace();
        }

        return objetos ;
    }

    public static Dolarsito obtenerDolar()
    {
        Dolarsito dolarsito = null;

        try{

            ObjectMapper mapper = new ObjectMapper();

            dolarsito = mapper.readValue(new URL("https://www.dolarito.ar/_next/data/XjlxzmsKE2OvL4QjuFLRH/dolar-hoy.json"), Dolarsito.class);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return dolarsito;
    }


    }






