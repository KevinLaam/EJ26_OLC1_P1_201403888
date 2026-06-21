package entorno;

import java.util.HashMap;
import instrucciones.Funcion;
public class Entorno {

    private HashMap<String, Simbolo> tabla;
    private HashMap<String, Funcion> funciones;
    private Entorno anterior;

    public Entorno() {
        tabla = new HashMap<>();
        funciones = new HashMap<>();
        anterior = null;
    }
    
    public Entorno(Entorno anterior) {
        tabla = new HashMap<>();
        funciones = new HashMap<>();
        this.anterior = anterior;
    }


    public boolean guardar(Simbolo simbolo){

        if(tabla.containsKey(simbolo.getId())){
            return false;
        }

        tabla.put(simbolo.getId(), simbolo);
        return true;
    }

    public Simbolo buscar(String id){
        //return tabla.get(id);
         Entorno actual = this;

        while (actual != null) {
            Simbolo simbolo = actual.tabla.get(id);

            if (simbolo != null) {
                return simbolo;
            }

            actual = actual.anterior;
        }

        return null;
    }
    
    public boolean guardarFuncion(String id, Funcion funcion) {

    if (funciones.containsKey(id)) {
        return false;
    }

    funciones.put(id, funcion);
    return true;
    }

    public Funcion buscarFuncion(String id) {

        Entorno actual = this;

        while (actual != null) {

            Funcion funcion = actual.funciones.get(id);

            if (funcion != null) {
                return funcion;
            }

            actual = actual.anterior;
        }

        return null;
    }
    //para el for
    public boolean actualizar(String id, Object nuevoValor) {
        Entorno actual = this;
        
       while (actual != null) {
            Simbolo simbolo = actual.tabla.get(id);

            if (simbolo != null) {
                simbolo.setValor(nuevoValor);
                return true;
            }

            actual = actual.anterior;
        }

        return false;
    }
}