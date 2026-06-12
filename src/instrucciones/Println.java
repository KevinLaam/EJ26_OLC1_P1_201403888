package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Consola;


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
    //mostraba en consola
    //System.out.println(valorFinal);
    //muestra en interfaz
    //estaba colocando Null cuando habia error
    //Consola.agregar(valorFinal);
        if (valorFinal != null) {
        Consola.agregar(valorFinal);
    }
    
    return null;
    }
}