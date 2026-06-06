package main;

import java.io.File;

public class Generador {

    public static void main(String[] args) {

        try {

            String lexer = "src/analizadores/Lexer.flex";

            jflex.Main.generate(new String[]{lexer});

            String[] cup = {
                "-parser",
                "Parser",
                "-symbols",
                "sym",
                "-destdir",
                "src/analizadores",
                "src/analizadores/Parser.cup"
            };

            java_cup.Main.main(cup);

            System.out.println("Analizadores generados correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}