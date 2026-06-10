package entorno;

public class Simbolo {

    private String id;
    private String tipo;
    private Object valor;

    public Simbolo(String id, String tipo, Object valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
