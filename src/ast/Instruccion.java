package ast;

import entorno.Entorno;

public abstract class Instruccion {

    public abstract Object ejecutar(Entorno entorno);

}