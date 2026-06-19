package ast;

import entorno.Entorno;

public abstract class Instruccion {
    //se agrego para mostrar linea y columna de donde estan los errores 
    public int linea;
    public int columna;

    public Instruccion() {
        this.linea = 0;
        this.columna = 0;
    }

    public Instruccion(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }
    
    //hasta aca se agrego

    public abstract Object ejecutar(Entorno entorno);

}