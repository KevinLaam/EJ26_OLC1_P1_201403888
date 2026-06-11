package instrucciones;

import ast.Instruccion;
import entorno.Entorno;

public class Break extends Instruccion {

    @Override
    public Object ejecutar(Entorno entorno) {
        return "break";
    }
}
