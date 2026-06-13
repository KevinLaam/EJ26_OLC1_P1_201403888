/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Bloque extends Instruccion {
    private LinkedList<Instruccion> instrucciones;

    public Bloque(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Entorno local = new Entorno(entorno);

        for (Instruccion instruccion : instrucciones) {

            Object resultado = instruccion.ejecutar(local);

            if (resultado instanceof Break || resultado instanceof Continue) {
                return resultado;
            }
        }

        return null;
    }
}
