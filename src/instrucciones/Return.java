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
import entorno.Entorno;

public class Return extends Instruccion {

    private Object valor;

    public Return(Object valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Object valorFinal = valor;

        if (valor instanceof Instruccion) {
            valorFinal = ((Instruccion) valor).ejecutar(entorno);
        }

        return new ReturnValue(valorFinal);
    }
}
