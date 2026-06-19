package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import java.util.LinkedList;

public class If extends Instruccion {

    private Object condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private LinkedList<Instruccion> instruccionesElse;

    public If(Object condicion, LinkedList<Instruccion> instruccionesIf, LinkedList<Instruccion> instruccionesElse) {
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Object valorCondicion;

        if (condicion instanceof Instruccion) {
            valorCondicion = ((Instruccion) condicion).ejecutar(entorno);
        } else {
            valorCondicion = condicion;
        }

        if (!(valorCondicion instanceof Boolean)) {
            System.out.println("Error semantico: la condicion del if debe ser booleana");
            return null;
        }

        if ((Boolean) valorCondicion) {
            
             Entorno entornoIf = new Entorno(entorno);

            for (Instruccion ins : instruccionesIf) {
                if (ins != null) {
                    //ins.ejecutar(entorno);
                    Object resultado = ins.ejecutar(entornoIf);

                    if ("break".equals(resultado) || "continue".equals(resultado)) {
                        return resultado;
                    }
                }
            }

        } else {
            
             Entorno entornoElse = new Entorno(entorno);

            if (instruccionesElse != null) {
                for (Instruccion ins : instruccionesElse) {
                    if (ins != null) {
                        //ins.ejecutar(entorno);
                        Object resultado = ins.ejecutar(entornoElse);

                        if ("break".equals(resultado) || "continue".equals(resultado)) {
                            return resultado;
                        }
                    }
                }
            }

        }

        return null;
    }
}