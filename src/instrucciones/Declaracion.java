package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;

public class Declaracion extends Instruccion {

    private String id;
    private String tipo;
    private Object valor;

    public Declaracion(String id, String tipo, Object valor){
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Simbolo simbolo =
                new Simbolo(id, tipo, valor);

        entorno.guardar(simbolo);

        return null;
    }
}