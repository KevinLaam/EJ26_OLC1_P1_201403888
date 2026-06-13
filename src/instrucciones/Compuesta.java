package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;

public class Compuesta extends Instruccion {

    private String id;
    private Object valor;
    private String operador;

    public Compuesta(
            String id,
            Object valor,
            String operador) {

        this.id = id;
        this.valor = valor;
        this.operador = operador;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Simbolo simbolo = entorno.buscar(id);

        if (simbolo == null) {
            System.out.println(
                "Error semantico: variable no existe -> " + id
            );
            return null;
        }

        Object actual = simbolo.getValor();

        Object nuevoValor = valor;

        if (valor instanceof Instruccion) {
            nuevoValor =
                ((Instruccion) valor).ejecutar(entorno);
        }

        if (operador.equals("+")||operador.equals("++")) {

           if (actual instanceof Integer) {

        if (nuevoValor instanceof Integer) {

            entorno.actualizar(
                    id,
                    (Integer) actual + (Integer) nuevoValor
            );

            return null;
        }

        System.out.println(
                "Error semantico: no se puede sumar float64 a int"
        );

        return null;
    }

    if (actual instanceof Double) {

        if (nuevoValor instanceof Integer
                || nuevoValor instanceof Double) {

            double a = ((Number) actual).doubleValue();
            double b = ((Number) nuevoValor).doubleValue();

            entorno.actualizar(id, a + b);

            return null;
        }

        System.out.println(
                "Error semantico: tipos incompatibles para +="
        );

        return null;
    }
        }

        if (operador.equals("-")||operador.equals("++")) {

             if (actual instanceof Integer) {

        if (nuevoValor instanceof Integer) {

            entorno.actualizar(
                    id,
                    (Integer) actual - (Integer) nuevoValor
            );

            return null;
        }

        System.out.println(
                "Error semantico: no se puede restar float64 a int"
        );

        return null;
    }

    if (actual instanceof Double) {

        if (nuevoValor instanceof Integer
                || nuevoValor instanceof Double) {

            double a = ((Number) actual).doubleValue();
            double b = ((Number) nuevoValor).doubleValue();

            entorno.actualizar(id, a - b);

            return null;
        }

        System.out.println(
                "Error semantico: tipos incompatibles para -="
        );

        return null;
    }
        }

        System.out.println(
                "Error semantico: asignacion compuesta invalida"
        );

        return null;
    }
}