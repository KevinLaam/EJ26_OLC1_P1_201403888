package expresiones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;

public class AccVariable extends Instruccion {

    private String id;

    public AccVariable(String id) {
        this.id = id;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        return obtenerValor(entorno);
    }

    public Object obtenerValor(Entorno entorno) {

        Simbolo simbolo = entorno.buscar(id);

        if (simbolo == null) {
            System.out.println("Error semantico: La variable " + id + " no existe");
            return null;
        }

        return simbolo.getValor();
    }
}