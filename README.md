# GoLite 

Proyecto desarrollado para el curso de Organización de Lenguajes y Compiladores 1.

## Descripción

GoLite es desarrollado en Java utilizando JFlex y CUP. El sistema realiza análisis léxico, sintáctico y semántico, mostrando los resultados en una interfaz gráfica.

## Características Implementadas

### Análisis Léxico
- Reconocimiento de palabras reservadas.
- Identificación de identificadores.
- Reconocimiento de números enteros y decimales.
- Reconocimiento de cadenas y caracteres.
- Operadores aritméticos.
- Operadores relacionales.
- Operadores lógicos.

### Análisis Sintáctico
- Declaración de variables.
- Asignaciones.
- Instrucciones de impresión.
- Estructuras condicionales IF.
- Ciclos FOR.
- BREAK.
- CONTINUE.

### Análisis Semántico
- Validación de tipos.
- Verificación de variables declaradas.
- Manejo de ámbitos (entornos).
- Reporte de errores semánticos.

### Interfaz Gráfica
- Editor de código.
- Carga de archivos.
- Consola de salida.
- Tabla de tokens.
- Tabla de errores.

---

## Tecnologías Utilizadas

- Java
- Swing
- JFlex
- Java CUP
- NetBeans IDE

---

## Estructura del Proyecto

```text
src/
│
├── analizadores/
│   ├── Lexer.java
│   ├── Parser.java
│   └── sym.java
│
├── ast/
│   └── Instruccion.java
│
├── entorno/
│   ├── Entorno.java
│   └── Simbolo.java
│
├── instrucciones/
│   ├── Declaracion.java
│   ├── Asignacion.java
│   ├── Println.java
│   ├── If.java
│   ├── For.java
│   ├── Break.java
│   └── Continue.java
│
├── expresiones/
│   ├── Operacion.java
│   └── AccesoVariable.java
│
└── ui/
    └── Principal.java
