package instrucciones;

import ast.Instruccion;
import entorno.Entorno;


public class Println extends Instruccion {

    private Object valor;

    public Println(Object valor) {
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        Object valorFinal = valor;

    if (valor instanceof Instruccion) {

        valorFinal =
                ((Instruccion) valor)
                        .ejecutar(entorno);

    }

    System.out.println(valorFinal);

    return null;
    }
}