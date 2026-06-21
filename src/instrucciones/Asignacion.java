package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import reportes.ReporteE;
public class Asignacion extends Instruccion {

    private String id;
    private Object valor;

    public Asignacion(String id, Object valor, int linea, int columna) {
        super(linea,columna);
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Simbolo simbolo = entorno.buscar(id);

        if (simbolo == null) {
            //System.out.println("Error semantico: la variable " + id + " no existe");
            ReporteE.agregarError(
                "SEMANTICO",
                "La variable '" + id + "' no existe",
                linea,
                columna
            );
            return null;
        }

        Object valorFinal = valor;

        if (valor instanceof Instruccion) {
            valorFinal = ((Instruccion) valor).ejecutar(entorno);
        }

        if (!validarTipo(simbolo.getTipo(), valorFinal)) {
            ReporteE.agregarError(
                 "SEMANTICO",
                 "No se puede asignar un valor de tipo "
                 + obtenerTipo(valorFinal)
                 + " a la variable '"
                 + id
                 + "' de tipo "
                 + simbolo.getTipo(),
                 linea,
                 columna
             );
            return null;
        }

        entorno.actualizar(id, valorFinal);

        return null;
    }

    private boolean validarTipo(String tipoDeclarado, Object valor) {

        if (tipoDeclarado.equals("int")) {
            return valor instanceof Integer;
        }

        if (tipoDeclarado.equals("float64")) {
            return valor instanceof Double;
        }

        if (tipoDeclarado.equals("string")) {
            return valor instanceof String;
        }

        if (tipoDeclarado.equals("bool")) {
            return valor instanceof Boolean;
        }

        return false;
    }

    private String obtenerTipo(Object valor) {

        if (valor instanceof Integer) return "int";
        if (valor instanceof Double) return "float64";
        if (valor instanceof String) return "string";
        if (valor instanceof Boolean) return "bool";

        return "desconocido";
    }
}