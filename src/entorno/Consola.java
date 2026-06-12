/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entorno;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Consola {
    
    public static ArrayList<String> salida = new ArrayList<>();

    public static void limpiar() {
        salida.clear();
    }

    public static void agregar(Object valor) {
        salida.add(String.valueOf(valor));
    }

    public static String obtenerTexto() {

        StringBuilder sb = new StringBuilder();

        for(String linea : salida){
            sb.append(linea).append("\n");
        }

        return sb.toString();
    }
    
}
