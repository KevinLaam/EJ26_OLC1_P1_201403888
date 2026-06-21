package instrucciones;

import ast.Instruccion;
import entorno.Entorno;
import reportes.ReporteE;

public class FuncionNativa extends Instruccion {

    private String tipo;
    private Object valor;

    public FuncionNativa(String tipo, Object valor, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        Object resultado = valor;

        if(valor instanceof Instruccion){
            resultado = ((Instruccion) valor).ejecutar(entorno);
        }

        switch(tipo){

            case "atoi":
                //return Integer.parseInt(resultado.toString());
                 try {
                return Integer.parseInt(resultado.toString());
            } catch (NumberFormatException ex) {
                ReporteE.agregarError(
                    "SEMANTICO",
                    "No se puede convertir a int: " + resultado,
                    linea,
                    columna
                );
                return null;
            }

            case "parsefloat":
                //return Double.parseDouble(resultado.toString());
                try {
                    return Double.parseDouble(resultado.toString());
                } catch (NumberFormatException ex) {
                    ReporteE.agregarError(
                        "SEMANTICO",
                        "No se puede convertir a float64: " + resultado,
                        linea,
                        columna
                    );
                    return null;
                }

            case "typeof":

                if(resultado instanceof Integer)
                    return "int";

                if(resultado instanceof Double)
                    return "float64";

                if(resultado instanceof Boolean)
                    return "bool";

                if(resultado instanceof Character)
                    return "rune";

                if(resultado instanceof String)
                    return "string";

                return "unknown";
        }

        return null;
    }
}