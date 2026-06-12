package reportes;

import java.util.LinkedList;

public class ReporteE {

    public static LinkedList<ErrorC> errores = new LinkedList<>();

    public static void agregarError(String tipo, String descripcion, int linea, int columna) {
        errores.add(new ErrorC(tipo, descripcion, linea, columna));
    }

    public static void limpiar() {
        errores.clear();
    }

    public static boolean hayErrores() {
        return !errores.isEmpty();
    }
    
    public static LinkedList<ErrorC> getErrores() {
        return errores;
    }

    public static void imprimirErrores() {
        if (errores.isEmpty()) {
            System.out.println("No hay errores.");
            return;
        }

        System.out.println("========= REPORTE DE ERRORES =========");

        for (ErrorC error : errores) {
            System.out.println(error);
        }
    }
}