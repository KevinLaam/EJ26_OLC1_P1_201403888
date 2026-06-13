package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import entorno.Consola;
import java.util.LinkedList;


public class Println extends Instruccion {

   // private Object valor; era para una sola expresion del print
    
    private LinkedList<Object> valores;

    public Println(LinkedList<Object> valores) {
        this.valores = valores;
    }

    @Override
    public Object ejecutar(Entorno entorno) {
        
        //Object valorFinal = valores;
        
         StringBuilder salida = new StringBuilder();

         for (Object valor : valores) {

            Object valorFinal = valor;

            if (valor instanceof Instruccion) {
                valorFinal = ((Instruccion) valor).ejecutar(entorno);
            }

            if (valorFinal != null) {
                salida.append(valorFinal);
            }
        }

        Consola.agregar(salida.toString());

        return null;
    }
    //mostraba en consola
    //System.out.println(valorFinal);
    //muestra en interfaz
    //estaba colocando Null cuando habia error
    //Consola.agregar(valorFinal);
    //    if (valorFinal != null) {
      //  Consola.agregar(valorFinal);
    //}
    
    //return null;
    
}