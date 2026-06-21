/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

/**
 *
 * @author Usuario
 */

import ast.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import java.util.LinkedList;
import reportes.ReporteE;

public class Funcion extends Instruccion {

    private String id;
    private LinkedList<Parametro> parametros;
    private String tipoRetorno;
    private LinkedList<Instruccion> instrucciones;

    public Funcion(
            String id,
            LinkedList<Parametro> parametros,
            String tipoRetorno,
            LinkedList<Instruccion> instrucciones,
            int linea,
            int columna) {

        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.tipoRetorno = tipoRetorno;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Entorno entorno) {

        boolean guardada = entorno.guardarFuncion(id, this);

        if (!guardada) {
            ReporteE.agregarError(
                    "SEMANTICO",
                    "La funcion '" + id + "' ya existe",
                    linea,
                    columna
            );
        }

        return null;
    }

    public Object ejecutarFuncion(Entorno entornoPadre, LinkedList<Object> argumentos) {

        if (argumentos.size() != parametros.size()) {
            ReporteE.agregarError(
                    "SEMANTICO",
                    "Cantidad incorrecta de parametros en funcion '" + id + "'",
                    linea,
                    columna
            );
            return null;
        }

        Entorno local = new Entorno(entornoPadre);

        for (int i = 0; i < parametros.size(); i++) {

            Parametro parametro = parametros.get(i);
            Object valor = argumentos.get(i);
            
            String tipoValor = obtenerTipo(valor);

            if (!parametro.getTipo().equals(tipoValor)) {
                ReporteE.agregarError(
                        "SEMANTICO",
                        "Parametro '" + parametro.getId() + "' esperaba "
                        + parametro.getTipo() + " y se obtuvo " + tipoValor,
                        linea,
                        columna
                );
                return null;
            }
            Simbolo simbolo = new Simbolo(
                    parametro.getId(),
                    parametro.getTipo(),
                    valor
            );

            boolean guardado = local.guardar(simbolo);

            if (!guardado) {
                ReporteE.agregarError(
                        "SEMANTICO",
                        "Parametro repetido '" + parametro.getId()
                        + "' en funcion '" + id + "'",
                        linea,
                        columna
                );

                return null;
            }
        }

        for (Instruccion ins : instrucciones) {

            Object resultado = ins.ejecutar(local);

            if (resultado instanceof ReturnValue) {

                ReturnValue retorno = (ReturnValue) resultado;

                if (tipoRetorno == null) {
                    ReporteE.agregarError(
                            "SEMANTICO",
                            "La funcion '" + id + "' no debe retornar valor",
                            linea,
                            columna
                    );
                    return null;
                }

                //return retorno.getValor();
                Object valorRetorno = retorno.getValor();

                String tipoValor = obtenerTipo(valorRetorno);

                if (!tipoRetorno.equals(tipoValor)) {
                    ReporteE.agregarError(
                            "SEMANTICO",
                            "La funcion '" + id + "' debe retornar " + tipoRetorno
                            + " y se obtuvo " + tipoValor,
                            linea,
                            columna
                    );
                    return null;
                }

                return valorRetorno;
            }
        }

        //return null;
        if (tipoRetorno != null) {
            ReporteE.agregarError(
                    "SEMANTICO",
                    "La funcion '" + id + "' debe retornar un valor de tipo " + tipoRetorno,
                    linea,
                    columna
            );
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public String getTipoRetorno() {
        return tipoRetorno;
    }
    
    private String obtenerTipo(Object valor) {

    if (valor instanceof Integer) {
        return "int";
    }

    if (valor instanceof Double) {
        return "float64";
    }

    if (valor instanceof Boolean) {
        return "bool";
    }

    if (valor instanceof String) {
        String texto = (String) valor;

        if (texto.length() == 1) {
            return "rune";
        }

        return "string";
    }

    return "desconocido";
}
}