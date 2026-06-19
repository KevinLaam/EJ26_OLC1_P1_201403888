package main;

import entorno.Entorno;
import instrucciones.Declaracion;

public class PruebaEntorno {

    public static void main(String[] args) {

        Entorno global = new Entorno();

        //Declaracion d1 = new Declaracion("a", "int", 100);
        //Declaracion d2 = new Declaracion("cadena", "string", "Hola");

        //d1.ejecutar(global);
        //d2.ejecutar(global);

        System.out.println(global.buscar("a").getValor());
        System.out.println(global.buscar("cadena").getValor());
    }
}