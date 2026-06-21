package expresiones;

import ast.Instruccion;
import entorno.Entorno;
import reportes.ReporteE;

public class Operacion extends Instruccion {

    private Object izquierda;
    private Object derecha;
    private String operador;
    

    public Operacion(Object izquierda, Object derecha, String operador, int linea, int columna) {
        super(linea, columna);
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.operador = operador;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Object valIzq = obtenerValor(izquierda, entorno);
        Object valDer = obtenerValor(derecha, entorno);

        switch (operador) {

            case "+":
                if (valIzq instanceof String || valDer instanceof String) {
                    return String.valueOf(valIzq) + String.valueOf(valDer);
                }

                if (valIzq instanceof Double || valDer instanceof Double) {
                    return convertirDouble(valIzq) + convertirDouble(valDer);
                }

                if (valIzq instanceof Integer && valDer instanceof Integer) {
                    return (Integer) valIzq + (Integer) valDer;
                }
                break;

            case "-":
                if (valIzq instanceof Double || valDer instanceof Double) {
                    return convertirDouble(valIzq) - convertirDouble(valDer);
                }

                if (valIzq instanceof Integer && valDer instanceof Integer) {
                    return (Integer) valIzq - (Integer) valDer;
                }
                break;

            case "*":
                if (valIzq instanceof Double || valDer instanceof Double) {
                    return convertirDouble(valIzq) * convertirDouble(valDer);
                }

                if (valIzq instanceof Integer && valDer instanceof Integer) {
                    return (Integer) valIzq * (Integer) valDer;
                }
                break;

            case "/":
                if (convertirDouble(valDer) == 0) {
                    //System.out.println("Error semantico: division entre cero");
                    //return null;
                    //se agrega para la division en 0
                    ReporteE.agregarError(
                    "SEMANTICO",
                    "No se puede dividir entre cero",
                    linea,
                    columna
                );
                return null;
                }

                if (valIzq instanceof Double || valDer instanceof Double) {
                    return convertirDouble(valIzq) / convertirDouble(valDer);
                }

                if (valIzq instanceof Integer && valDer instanceof Integer) {
                    return (Integer) valIzq / (Integer) valDer;
                }
                 ReporteE.agregarError(
                    "SEMANTICO",
                    "Operación inválida para división",
                    linea,
                    columna
                );
                return null;
                
               

            case "%":
                if (valDer instanceof Integer && (Integer) valDer == 0) {
                    ReporteE.agregarError(
                        "SEMANTICO",
                        "No se puede calcular el módulo por cero",
                        linea,
                        columna
                    );
                    return null;
                }

                if (valIzq instanceof Integer && valDer instanceof Integer) {
                    return (Integer) valIzq % (Integer) valDer;
                }

                ReporteE.agregarError(
                    "SEMANTICO",
                    "Operación inválida para módulo",
                    linea,
                    columna
                );
                return null;
                
            case "&&":
                if (valIzq instanceof Boolean && valDer instanceof Boolean) {
                    return (Boolean) valIzq && (Boolean) valDer;
                }
                break;

            case "||":
                if (valIzq instanceof Boolean && valDer instanceof Boolean) {
                    return (Boolean) valIzq || (Boolean) valDer;
                }
                break;

            case "!":
                if (valIzq instanceof Boolean) {
                    return !(Boolean) valIzq;
                }
                break;
                
            case ">":
                return convertirDouble(valIzq) >
                       convertirDouble(valDer);

            case "<":
                return convertirDouble(valIzq) <
                       convertirDouble(valDer);

            case ">=":
                return convertirDouble(valIzq) >=
                       convertirDouble(valDer);

            case "<=":
                return convertirDouble(valIzq) <=
                       convertirDouble(valDer);

            case "==":
                if (valIzq == null && valDer == null) {
                    return true;
                }
                if (valIzq == null || valDer == null) {
                    return false;
                }
                return valIzq.equals(valDer);

            case "!=":
                if (valIzq == null && valDer == null) {
                    return false;
                }
                if (valIzq == null || valDer == null) {
                    return true;
                }
                return !valIzq.equals(valDer);

                
        }

        System.out.println("Error semantico: operacion aritmetica invalida");
        return null;
    }

    private Object obtenerValor(Object valor, Entorno entorno) {

        if (valor instanceof Instruccion) {
            return ((Instruccion) valor).ejecutar(entorno);
        }

        return valor;
    }

    private double convertirDouble(Object valor) {

        if (valor instanceof Integer) {
            return ((Integer) valor).doubleValue();
        }

        if (valor instanceof Double) {
            return (Double) valor;
        }

        return 0;
    }
}