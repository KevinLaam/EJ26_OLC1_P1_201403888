package main;

import analizadores.Lexer;
import analizadores.Parser;
import ast.Instruccion;
import entorno.Entorno;
import java.io.StringReader;
import java.util.LinkedList;
import reportes.ReporteE;
import reportes.ReporteT;

public class PruebaParser {

    public static void main(String[] args) {

       String entrada = """
        func main() {
            var bandera bool = 100
        }
        """;

        try {
            ReporteE.limpiar();
            ReporteE.limpiar();
            ReporteT.limpiar();

            Lexer lexer = new Lexer(new StringReader(entrada));
            Parser parser = new Parser(lexer);

           java_cup.runtime.Symbol resultadoParser = parser.parse();

            if (resultadoParser == null || resultadoParser.value == null) {
                ReporteT.imprimir();
                ReporteE.imprimirErrores();
                return;
            }

            Object resultado = resultadoParser.value;
            ReporteT.imprimir();
            
            if (ReporteE.hayErrores()) {
                ReporteE.imprimirErrores();
                return;
            }

            LinkedList<Instruccion> lista =
                    (LinkedList<Instruccion>) resultado;

            System.out.println("Analisis correcto");
            System.out.println("Cantidad instrucciones: " + lista.size());

            Entorno global = new Entorno();

            for (Instruccion ins : lista) {
                ins.ejecutar(global);
            }

            ReporteE.imprimirErrores();


        } catch (Exception e) {
            System.out.println("Error al analizar:");
            System.out.println(e.getMessage());

            ReporteE.imprimirErrores();
        }
    }
}