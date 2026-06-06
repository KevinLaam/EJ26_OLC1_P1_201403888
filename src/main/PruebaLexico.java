package main;

import analizadores.Lexer;
import java.io.StringReader;
import java_cup.runtime.Symbol;

public class PruebaLexico {

    public static void main(String[] args) {
        try {
            String entrada = """
                func main() {
                   var a int = 100
                    
                        var @b float64 = 3.14
                    
                        cadena := "Hola"
                    
                        letra := 'A'
                    
                        bandera := true
                        // Esto es un comentario de una línea 
                             /* 
                             Esto es un comentario multilínea 
                             */     
                }
                """;

            Lexer lexer = new Lexer(new StringReader(entrada));
            Symbol token;

            while ((token = lexer.next_token()).sym != analizadores.sym.EOF) {
                System.out.println(
                    "Token: " + token.sym +
                    " | Valor: " + token.value +
                    " | Línea: " + token.left +
                    " | Columna: " + token.right
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}