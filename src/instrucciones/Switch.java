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
import java.util.LinkedList;

public class Switch extends Instruccion {

    private Object expresion;
    private LinkedList<CaseSwitch> casos;
    private LinkedList<Instruccion> instruccionesDefault;

    public Switch(
            Object expresion,
            LinkedList<CaseSwitch> casos,
            LinkedList<Instruccion> instruccionesDefault,
            int linea,
            int columna) {

        super(linea, columna);

        this.expresion = expresion;
        this.casos = casos;
        this.instruccionesDefault = instruccionesDefault;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Object valorSwitch = expresion;

        if (expresion instanceof Instruccion) {
            valorSwitch =
                    ((Instruccion) expresion)
                            .ejecutar(entorno);
        }

        for (CaseSwitch caso : casos) {

            Object valorCase = caso.getValor();

            if (valorCase instanceof Instruccion) {
                valorCase =
                        ((Instruccion) valorCase)
                                .ejecutar(entorno);
            }

            if (valorSwitch.equals(valorCase)) {

                for (Instruccion ins : caso.getInstrucciones()) {

                    Object resultado = ins.ejecutar(entorno);

                    if (resultado instanceof Break) {
                        return null;
                    }
                }

                return null;
            }
        }

        if (instruccionesDefault != null) {

            for (Instruccion ins : instruccionesDefault) {
                ins.ejecutar(entorno);
            }
        }

        return null;
    }
}