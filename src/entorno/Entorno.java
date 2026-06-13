package entorno;

import java.util.HashMap;

public class Entorno {

    private HashMap<String, Simbolo> tabla;
    private Entorno anterior;

    public Entorno() {
        tabla = new HashMap<>();
        anterior = null;
    }
    
    public Entorno(Entorno anterior) {
        tabla = new HashMap<>();
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