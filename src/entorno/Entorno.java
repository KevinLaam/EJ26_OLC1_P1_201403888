package entorno;

import java.util.HashMap;

public class Entorno {

    private HashMap<String, Simbolo> tabla;

    public Entorno() {
        tabla = new HashMap<>();
    }

    public boolean guardar(Simbolo simbolo){

        if(tabla.containsKey(simbolo.getId())){
            return false;
        }

        tabla.put(simbolo.getId(), simbolo);
        return true;
    }

    public Simbolo buscar(String id){
        return tabla.get(id);
    }
    //para el for
    public boolean actualizar(String id, Object nuevoValor) {

    Simbolo simbolo = tabla.get(id);

        if (simbolo == null) {
            return false;
        }

        simbolo.setValor(nuevoValor);
        return true;
    }
}