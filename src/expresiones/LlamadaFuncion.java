/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

/**
 *
 * @author Usuario
 */

import ast.Instruccion;
import entorno.Entorno;
import instrucciones.Funcion;
import java.util.LinkedList;
import reportes.ReporteE;



public class LlamadaFuncion extends Instruccion {

     private String id;
    private LinkedList<Object> argumentos;

    public LlamadaFuncion(String id, LinkedList<Object> argumentos, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.argumentos = argumentos;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Funcion funcion = entorno.buscarFuncion(id);

        if (funcion == null) {
            ReporteE.agregarError(
                    "SEMANTICO",
                    "La funcion '" + id + "' no existe",
                    linea,
                    columna
            );
            return null;
        }

        LinkedList<Object> valores = new LinkedList<>();

        for (Object arg : argumentos) {
            if (arg instanceof Instruccion) {
                valores.add(((Instruccion) arg).ejecutar(entorno));
            } else {
                valores.add(arg);
            }
        }

        return funcion.ejecutarFuncion(entorno, valores);
    }
}
