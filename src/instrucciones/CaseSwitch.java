/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

/**
 *
 * @author Usuario
 */

import ast.Instruccion;
import java.util.LinkedList;

public class CaseSwitch {
     private Object valor;
    private LinkedList<Instruccion> instrucciones;

    public CaseSwitch(
            Object valor,
            LinkedList<Instruccion> instrucciones) {

        this.valor = valor;
        this.instrucciones = instrucciones;
    }

    public Object getValor() {
        return valor;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }
}
