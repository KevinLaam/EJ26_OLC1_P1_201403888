package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import reportes.ReporteE;


public class Declaracion extends Instruccion {

    private String id;
    private String tipo;
    private Object valor;

    public Declaracion(String id, String tipo, Object valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        
        Object valorFinal = valor;
        
            if (valor instanceof Instruccion) {
            valorFinal = ((Instruccion) valor).ejecutar(entorno);
            }
            
            //para validar desconocidos 
            if (tipo.equals("desconocido")) {
                tipo = obtenerTipo(valorFinal);
            }
            //para variables sin valor inicial 
             if (valorFinal == null) {
                valorFinal = valorPorDefecto(tipo);
            }
        
            if (!validarTipo(tipo, valorFinal)) {
            ReporteE.agregarError(
                "SEMANTICO",
                "No se puede asignar un valor de tipo " + valorFinal +
                " a una variable de tipo " + tipo,
                0,
                0
            );

            return null;
        }

        Simbolo simbolo = new Simbolo(id, tipo, valorFinal);

        boolean guardado = entorno.guardar(simbolo);

        if (!guardado) {
            ReporteE.agregarError(
                "SEMANTICO",
                "La variable '" + id + "' ya existe",
                0,
                0
            );
        }

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
    //para variables sin inicio
    private Object valorPorDefecto(String tipo) {

    if (tipo.equals("int")) {
        return 0;
    }

    if (tipo.equals("float64")) {
        return 0.0;
    }

    if (tipo.equals("string")) {
        return "";
    }

    if (tipo.equals("bool")) {
        return false;
    }

    return null;
}

    private String obtenerTipo(Object valor) {

        if (valor instanceof Integer) return "int";
        if (valor instanceof Double) return "float64";
        if (valor instanceof String) return "string";
        if (valor instanceof Boolean) return "bool";

        return "desconocido";
    }
}