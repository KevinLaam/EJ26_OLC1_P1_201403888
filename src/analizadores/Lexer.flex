package analizadores;

import java_cup.runtime.Symbol;
import reportes.ReporteE;
import reportes.ReporteT;

%%

%class Lexer
%public
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

/* ---------- EXPRESIONES REGULARES ---------- */

LETRA       = [a-zA-Z_]
ID_INVALIDO = [0-9]+[a-zA-Z_][a-zA-Z0-9_]*
DIGITO      = [0-9]
ID          = {LETRA}({LETRA}|{DIGITO})*

ENTERO      = {DIGITO}+
DECIMAL     = {DIGITO}+"."{DIGITO}+

CADENA      = \"([^\\\"]|\\n|\\r|\\t|\\\"|\\\\)*\"
CARACTER    = \'([^\\\']|\\n|\\r|\\t|\\\'|\\\\)\'

BLANCO      = [ \t\r\n]+

COMENTARIO_LINEA = "//".*
COMENTARIO_MULTI = "/*"([^*]|\*+[^*/])*\*+"/"

%%

/* ---------- PALABRAS RESERVADAS ---------- */

"func"              { ReporteT.agregar(yytext(), "FUNC", yyline + 1, yycolumn + 1);
                        return symbol(sym.FUNC);}
"main"              { ReporteT.agregar(yytext(), "MAIN", yyline + 1, yycolumn + 1);
                        return symbol(sym.MAIN); }
"var"               { ReporteT.agregar(yytext(), "VAR", yyline + 1, yycolumn + 1);
                        return symbol(sym.VAR); }

"if"                { ReporteT.agregar(yytext(), "IF", yyline + 1, yycolumn + 1);
                        return symbol(sym.IF); }
"else"              { ReporteT.agregar(yytext(), "ELSE", yyline + 1, yycolumn + 1);
                        return symbol(sym.ELSE); }
"for"               { ReporteT.agregar(yytext(), "FOR", yyline + 1, yycolumn + 1);
                        return symbol(sym.FOR); }
"break"             { ReporteT.agregar(yytext(), "BREAK", yyline + 1, yycolumn + 1);
                        return symbol(sym.BREAK); }
"continue"          { ReporteT.agregar(yytext(), "CONTINUE", yyline + 1, yycolumn + 1);
                        return symbol(sym.CONTINUE); }

"return"            { ReporteT.agregar(yytext(), "RETURN", yyline + 1, yycolumn + 1);
                        return symbol(sym.RETURN); 
                    }

"int"               { ReporteT.agregar(yytext(), "INT", yyline + 1, yycolumn + 1);
                        return symbol(sym.INT); }
"float64"           { ReporteT.agregar(yytext(), "FLOAT64", yyline + 1, yycolumn + 1);
                        return symbol(sym.FLOAT64); }
"string"            { ReporteT.agregar(yytext(), "STRING", yyline + 1, yycolumn + 1);
                        return symbol(sym.STRING); }
"bool"              { ReporteT.agregar(yytext(), "BOOL", yyline + 1, yycolumn + 1);
                        return symbol(sym.BOOL); }
"rune"              { ReporteT.agregar(yytext(), "RUNE", yyline + 1, yycolumn + 1);
                        return symbol(sym.RUNE); }

"true"              { ReporteT.agregar(yytext(), "TRUE", yyline + 1, yycolumn + 1);
                        return symbol(sym.TRUE); }
"false"             { ReporteT.agregar(yytext(), "FALSE", yyline + 1, yycolumn + 1);
                        return symbol(sym.FALSE); }
"nil"               { ReporteT.agregar(yytext(), "NIL", yyline + 1, yycolumn + 1);
                        return symbol(sym.NIL); }

/* ---------- FUNCIONES EMBEBIDAS ---------- */

"fmt.Println"               { ReporteT.agregar(yytext(), "PRINTLN", yyline + 1, yycolumn + 1);
                                return symbol(sym.PRINTLN); }
"strconv.Atoi"              { ReporteT.agregar(yytext(), "ATOI", yyline + 1, yycolumn + 1);
                                return symbol(sym.ATOI); }
"strconv.ParseFloat"        { ReporteT.agregar(yytext(), "PARSEFLOAT", yyline + 1, yycolumn + 1);
                                return symbol(sym.PARSEFLOAT); }
"reflect.TypeOf"            { ReporteT.agregar(yytext(), "TYPEOF", yyline + 1, yycolumn + 1);
                                return symbol(sym.TYPEOF); }
".String"                   { ReporteT.agregar(yytext(), "DOTSTRING", yyline + 1, yycolumn + 1);
                                return symbol(sym.DOTSTRING); }

/* ---------- OPERADORES COMPUESTOS ---------- */

":="                { ReporteT.agregar(yytext(), "DECLARACION", yyline + 1, yycolumn + 1);
                        return symbol(sym.DECLARACION); }
"+="                { ReporteT.agregar(yytext(), "MAS_IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.MAS_IGUAL); }
"-="                { ReporteT.agregar(yytext(), "MENOS_IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.MENOS_IGUAL); }

"=="                { ReporteT.agregar(yytext(), "IGUAL_IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.IGUAL_IGUAL); }
"!="                { ReporteT.agregar(yytext(), "DIFERENTE", yyline + 1, yycolumn + 1);
                        return symbol(sym.DIFERENTE); }
"<="                { ReporteT.agregar(yytext(), "MENOR_IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.MENOR_IGUAL); }
">="                { ReporteT.agregar(yytext(), "MAYOR_IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.MAYOR_IGUAL); }

"&&"                { ReporteT.agregar(yytext(), "AND", yyline + 1, yycolumn + 1);
                        return symbol(sym.AND); }
"||"                { ReporteT.agregar(yytext(), "OR", yyline + 1, yycolumn + 1);
                        return symbol(sym.OR); }

"++"                {ReporteT.agregar(yytext(), "INCREMENTO", yyline + 1, yycolumn + 1);
                         return symbol(sym.INCREMENTO); }
"--"                { ReporteT.agregar(yytext(), "DECREMENTO", yyline + 1, yycolumn + 1);
                        return symbol(sym.DECREMENTO); }

/* ---------- OPERADORES SIMPLES ---------- */

"="                 { ReporteT.agregar(yytext(), "IGUAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.IGUAL); }
"+"                 { ReporteT.agregar(yytext(), "MAS", yyline + 1, yycolumn + 1);
                        return symbol(sym.MAS); }
"-"                 { ReporteT.agregar(yytext(), "MENOS", yyline + 1, yycolumn + 1);
                        return symbol(sym.MENOS); }
"*"                 { ReporteT.agregar(yytext(), "POR", yyline + 1, yycolumn + 1);
                        return symbol(sym.POR); }
"/"                 { ReporteT.agregar(yytext(), "DIV", yyline + 1, yycolumn + 1);
                        return symbol(sym.DIV); }
"%"                 { ReporteT.agregar(yytext(), "MOD", yyline + 1, yycolumn + 1);
                        return symbol(sym.MOD); }

"!"                 { ReporteT.agregar(yytext(), "NOT", yyline + 1, yycolumn + 1);
                        return symbol(sym.NOT); }
"<"                 { ReporteT.agregar(yytext(), "MENOR", yyline + 1, yycolumn + 1);
                        return symbol(sym.MENOR); }
">"                 { ReporteT.agregar(yytext(), "MAYOR", yyline + 1, yycolumn + 1);
                        return symbol(sym.MAYOR); }

/* ---------- SIGNOS ---------- */

"("                 { ReporteT.agregar(yytext(), "PAR_A", yyline + 1, yycolumn + 1);
                        return symbol(sym.PAR_A); }
")"                 { ReporteT.agregar(yytext(), "PAR_C", yyline + 1, yycolumn + 1);
                        return symbol(sym.PAR_C); }
"{"                 { ReporteT.agregar(yytext(), "LLAVE_A", yyline + 1, yycolumn + 1);
                        return symbol(sym.LLAVE_A); }
"}"                 { ReporteT.agregar(yytext(), "LLAVE_C", yyline + 1, yycolumn + 1);
                        return symbol(sym.LLAVE_C); }

","                 { ReporteT.agregar(yytext(), "COMA", yyline + 1, yycolumn + 1);
                        return symbol(sym.COMA); }
";"                 { ReporteT.agregar(yytext(), "PUNTO_COMA", yyline + 1, yycolumn + 1);
                        return symbol(sym.PUNTO_COMA); }
"."                 { ReporteT.agregar(yytext(), "PUNTO", yyline + 1, yycolumn + 1);
                        return symbol(sym.PUNTO); }

/* ---------- LITERALES ---------- */

{ID_INVALIDO}       { ReporteE.agregarError("LEXICO","Identificador inválido: " + yytext(),yyline + 1,yycolumn + 1);}

{DECIMAL}           { ReporteT.agregar(yytext(), "DECIMAL", yyline + 1, yycolumn + 1);
                        return symbol(sym.DECIMAL, Double.parseDouble(yytext())); }
{ENTERO}            { ReporteT.agregar(yytext(), "ENTERO", yyline + 1, yycolumn + 1);
                        return symbol(sym.ENTERO, Integer.parseInt(yytext())); }

{CADENA}            {
                        String texto = yytext();
                        texto = texto.substring(1, texto.length() - 1);
                        ReporteT.agregar(yytext(), "CADENA", yyline + 1, yycolumn + 1);
                        return symbol(sym.CADENA, texto);
                    }
{CARACTER}          {
                        String texto = yytext();
                        texto = texto.substring(1, texto.length() - 1);
                        ReporteT.agregar(yytext(), "CARACTER", yyline + 1, yycolumn + 1);
                        return symbol(sym.CARACTER, texto);
                    }

/* ---------- IDENTIFICADORES ---------- */

{ID}                { ReporteT.agregar(yytext(), "ID", yyline + 1, yycolumn + 1);
                        return symbol(sym.ID, yytext()); }

/* ---------- COMENTARIOS Y ESPACIOS ---------- */

{COMENTARIO_LINEA}  { }
{COMENTARIO_MULTI}  { }
{BLANCO}            { }

/* ---------- ERRORES LÉXICOS ---------- */

. {
    ReporteE.agregarError(
        "LEXICO",
        "Caracter no reconocido: " + yytext(),
        yyline + 1,
        yycolumn + 1
    );
}