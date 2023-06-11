package com.tp.tp_final_lab3.Repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.tp_final_lab3.Models.*;
import com.tp.tp_final_lab3.Models.ApiCotizaciones.ExchangeRates;

import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;


public class Jackson {

    public static <T> void serializar(T objeto,String pathJson)
    {
        File file = new File(pathJson);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file,objeto);

        }catch (Exception e)
        {
            System.out.println("entro el catch");
            e.printStackTrace();
        }
    }

    public static <T> ArrayList<T> deserializarArrayListUser(String pathJson){

        File file = new File(pathJson);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        ArrayList<T> objetos = null;

        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Usuario.class);
            objetos = mapper.readValue(file, collectionType);
            Usuario.ultimoId = getUltimoUsersID((ArrayList<Usuario>)objetos); //pierde genericidad

        } catch (IOException e){

           e.printStackTrace();
        }

        return objetos ;
    }
    public static int getUltimoUsersID(ArrayList<Usuario> usuarios){
        return usuarios.get(usuarios.size()-1).getId();
    }

    public static <T> ArrayList<T> deserializarArrayListPedido(String pathJson)///poner clase
    {

        File file = new File(pathJson);

        ObjectMapper mapper = new ObjectMapper();

        ArrayList<T> objetos = null;

        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Pedido.class);
            objetos = mapper.readValue(file, collectionType);

        } catch (IOException e){

            e.printStackTrace();


            //dd
        }

        return objetos ;
    }


    public static ExchangeRates obtenerDivisas()
    {
        ExchangeRates exchangeRates = null;

        try{

            ObjectMapper mapper = new ObjectMapper();

            exchangeRates = mapper.readValue(new URL("https://api.bluelytics.com.ar/v2/latest"), ExchangeRates.class);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return exchangeRates;
    }
    }






