package instrucciones;

import ast.Instruccion;
import entorno.Entorno;

public class Continue extends Instruccion {

    @Override
    public Object ejecutar(Entorno entorno) {
        return "continue";
    }
}