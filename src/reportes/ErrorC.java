package reportes;

public class ErrorC {

    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;

    public ErrorC(String tipo, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return tipo + " | " + descripcion + " | Linea: " + linea + " | Columna: " + columna;
    }
}