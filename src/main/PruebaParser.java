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
            // Variable global 
            i := 10 
            z := 0 
            // Imprime 10 
            fmt.Println("Valor de i en el ámbito global:", i) 
            // Bloque independiente 
            { 
            // Variable local al bloque 
            j := 20 
            // Imprime 20 
            fmt.Println("Valor de j en el bloque independiente:", j) 
            // Imprime 10 
            fmt.Println("Acceso a i desde el bloque independiente:", i) 
            } 
            // Modifica i usando j 
            i = i + j 
            // Imprime 30 
            fmt.Println("Nuevo valor de i después de modificarlo en el bloque:", i) 
            // Variable con el mismo nombre que variable en entorno superior 
            z := 40 
            // Imprime 40 
            fmt.Println("Valor de z en el bloque independiente:", z) 
            // Imprime 0 
            fmt.Println("Valor de z fuera del bloque independiente:", z) 
            // Imprime 30 
            fmt.Println("Valor de i fuera del bloque:", i)
            // fmt.Println("Valor de j fuera del bloque:", j) 
            // Error: j no es accesible aquí 
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

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
