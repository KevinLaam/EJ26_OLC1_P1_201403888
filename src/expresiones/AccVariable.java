package expresiones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import reportes.ReporteE;

public class AccVariable extends Instruccion {

    private String id;

    public AccVariable(String id, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        return obtenerValor(entorno);
    }

    public Object obtenerValor(Entorno entorno) {

        Simbolo simbolo = entorno.buscar(id);

        if (simbolo == null) {

            ReporteE.agregarError(
                "SEMANTICO",
                "La variable '" + id + "' no existe",
                linea,
                columna
            );

            return null;
        }

        return simbolo.getValor();
    }
}