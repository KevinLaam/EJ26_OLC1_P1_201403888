/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ReporteT {
    
    private static ArrayList<Token> tokens = new ArrayList<>();

    public static void agregar(String lexema, String tipo, int linea, int columna) {
        tokens.add(new Token(lexema, tipo, linea, columna));
    }

    public static ArrayList<Token> getTokens() {
        return tokens;
    }

    public static void limpiar() {
        tokens.clear();
    }

    public static void imprimir() {
        System.out.println("========== TABLA DE TOKENS ==========");
        System.out.printf("%-20s %-25s %-10s %-10s%n", "LEXEMA", "TIPO", "LINEA", "COLUMNA");
        System.out.println("---------------------------------------------------------------");

        for (Token token : tokens) {
            System.out.printf(
                "%-20s %-25s %-10d %-10d%n",
                token.getLexema(),
                token.getTipo(),
                token.getLinea(),
                token.getColumna()
            );
        }

        System.out.println("=====================================");
    }
    
}
