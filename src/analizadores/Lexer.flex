package analizadores;

import java_cup.runtime.Symbol;

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

"func"              { return symbol(sym.FUNC); }
"main"              { return symbol(sym.MAIN); }
"var"               { return symbol(sym.VAR); }

"if"                { return symbol(sym.IF); }
"else"              { return symbol(sym.ELSE); }
"for"               { return symbol(sym.FOR); }
"break"             { return symbol(sym.BREAK); }
"continue"          { return symbol(sym.CONTINUE); }

"int"               { return symbol(sym.INT); }
"float64"           { return symbol(sym.FLOAT64); }
"string"            { return symbol(sym.STRING); }
"bool"              { return symbol(sym.BOOL); }
"rune"              { return symbol(sym.RUNE); }

"true"              { return symbol(sym.TRUE, true); }
"false"             { return symbol(sym.FALSE, false); }
"nil"               { return symbol(sym.NIL); }

/* ---------- FUNCIONES EMBEBIDAS ---------- */

"fmt.Println"               { return symbol(sym.PRINTLN); }
"strconv.Atoi"              { return symbol(sym.ATOI); }
"strconv.ParseFloat"        { return symbol(sym.PARSEFLOAT); }
"reflect.TypeOf"            { return symbol(sym.TYPEOF); }
".String"                   { return symbol(sym.DOTSTRING); }

/* ---------- OPERADORES COMPUESTOS ---------- */

":="                { return symbol(sym.DECLARACION); }
"+="                { return symbol(sym.MAS_IGUAL); }
"-="                { return symbol(sym.MENOS_IGUAL); }

"=="                { return symbol(sym.IGUAL_IGUAL); }
"!="                { return symbol(sym.DIFERENTE); }
"<="                { return symbol(sym.MENOR_IGUAL); }
">="                { return symbol(sym.MAYOR_IGUAL); }

"&&"                { return symbol(sym.AND); }
"||"                { return symbol(sym.OR); }

/* ---------- OPERADORES SIMPLES ---------- */

"="                 { return symbol(sym.IGUAL); }
"+"                 { return symbol(sym.MAS); }
"-"                 { return symbol(sym.MENOS); }
"*"                 { return symbol(sym.POR); }
"/"                 { return symbol(sym.DIV); }
"%"                 { return symbol(sym.MOD); }

"!"                 { return symbol(sym.NOT); }
"<"                 { return symbol(sym.MENOR); }
">"                 { return symbol(sym.MAYOR); }

/* ---------- SIGNOS ---------- */

"("                 { return symbol(sym.PAR_A); }
")"                 { return symbol(sym.PAR_C); }
"{"                 { return symbol(sym.LLAVE_A); }
"}"                 { return symbol(sym.LLAVE_C); }

","                 { return symbol(sym.COMA); }
";"                 { return symbol(sym.PUNTO_COMA); }
"."                 { return symbol(sym.PUNTO); }

/* ---------- LITERALES ---------- */

{DECIMAL}           { return symbol(sym.DECIMAL, Double.parseDouble(yytext())); }
{ENTERO}            { return symbol(sym.ENTERO, Integer.parseInt(yytext())); }

{CADENA}            {
                        String texto = yytext();
                        texto = texto.substring(1, texto.length() - 1);
                        return symbol(sym.CADENA, texto);
                    }
{CARACTER}          {
                        String texto = yytext();
                        texto = texto.substring(1, texto.length() - 1);
                        return symbol(sym.CARACTER, texto);
                    }

/* ---------- IDENTIFICADORES ---------- */

{ID}                { return symbol(sym.ID, yytext()); }

/* ---------- COMENTARIOS Y ESPACIOS ---------- */

{COMENTARIO_LINEA}  { }
{COMENTARIO_MULTI}  { }
{BLANCO}            { }

/* ---------- ERRORES LÉXICOS ---------- */

. {
    System.out.println("Error léxico: '" + yytext() + 
                       "' línea " + (yyline + 1) + 
                       ", columna " + (yycolumn + 1));
}