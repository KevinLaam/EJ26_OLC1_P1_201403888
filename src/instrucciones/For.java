package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import java.util.LinkedList;

public class For extends Instruccion {

    private Instruccion inicializacion;
    private Object condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(
            Instruccion inicializacion,
            Object condicion,
            Instruccion actualizacion,
            LinkedList<Instruccion> instrucciones) {

        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        Entorno entornoFor = new Entorno(entorno);
        //inicializacion.ejecutar(entorno);
        if (inicializacion != null) {
            inicializacion.ejecutar(entornoFor);
        }

        while (true) {

            Object valorCondicion;

            if (condicion instanceof Instruccion) {
                valorCondicion =
                        ((Instruccion) condicion)
                                .ejecutar(entornoFor);
            } else {
                valorCondicion = condicion;
            }

            if (!(valorCondicion instanceof Boolean)) {
                System.out.println(
                        "Error semantico: condicion del for no booleana"
                );
                return null;
            }

            if (!((Boolean) valorCondicion)) {
                break;
            }
             Entorno entornoIteracion = new Entorno(entornoFor);
             
            for (Instruccion ins : instrucciones) {

                 if (ins != null) {

                    Object resultado = ins.ejecutar(entornoIteracion);

                    if ("break".equals(resultado)) {
                        return null;
                    }

                    if ("continue".equals(resultado)) {
                        break;
                    }
                }
            }

            //actualizacion.ejecutar(entorno);
            if (actualizacion != null) {
                actualizacion.ejecutar(entornoFor);
            }
        }

        return null;
    }
}
