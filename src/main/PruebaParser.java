package main;

import analizadores.Lexer;
import analizadores.Parser;
import ast.Instruccion;
import java.io.StringReader;
import java.util.LinkedList;
import entorno.Entorno;

public class PruebaParser {

    public static void main(String[] args) {

        String entrada = """
        func main() {
            var a int = 100
            cadena := "Hola"
            bandera := true
        }
        """;

        try {
            Lexer lexer = new Lexer(new StringReader(entrada));
            Parser parser = new Parser(lexer);

            LinkedList<Instruccion> lista =
                    (LinkedList<Instruccion>) parser.parse().value;

            System.out.println("Analisis correcto");
            System.out.println("Cantidad instrucciones: " + lista.size());
        
            Entorno global = new Entorno();

            for(Instruccion ins : lista){
                ins.ejecutar(global);
            }

            System.out.println(global.buscar("a").getValor());
            System.out.println(global.buscar("cadena").getValor());
            System.out.println(global.buscar("bandera").getValor());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
