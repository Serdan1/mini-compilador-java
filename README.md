# Mini-Compilador en Java

Mini-compilador desarrollado en Java como proyecto académico para la asignatura de Lenguajes y Compiladores. El sistema implementa las tres fases fundamentales de un compilador: análisis léxico, análisis sintáctico y análisis semántico.

## Características

* Análisis léxico mediante clasificación de tokens.
* Parser recursivo descendente basado en una gramática simplificada.
* Verificación semántica utilizando una tabla de símbolos.
* Detección de errores léxicos, sintácticos y semánticos.
* Entrada de código por consola.
* Arquitectura modular y orientada a objetos.

## Tecnologías utilizadas

* Java 11
* Visual Studio Code
* Git y GitHub

## Estructura del proyecto

```text
mini-compilador-java
│
├── src
│   ├── Main.java
│   ├── Lexer.java
│   ├── Parser.java
│   ├── SemanticAnalyzer.java
│   ├── Symbol.java
│   ├── SymbolTable.java
│   ├── Token.java
│   └── TokenType.java
│
├── README.md
└── .gitignore
```

## Funcionamiento del compilador

El compilador procesa el código fuente siguiendo las siguientes fases:

1. **Análisis léxico**

   * Convierte el código fuente en una secuencia de tokens.

2. **Análisis sintáctico**

   * Verifica que la estructura del programa cumpla la gramática definida.

3. **Análisis semántico**

   * Comprueba la correcta declaración e inicialización de variables.

## Gramática implementada

```text
Stmt → ID '=' Expr ';'
Stmt → 'print' '(' Expr ')' ';'

Expr → Term { ('+' | '-') Term }

Term → Factor { ('*' | '/') Factor }

Factor → ID | NUM | '(' Expr ')'
```

## Ejemplo de ejecución correcta

### Entrada

```java
int x;
x = 5;
print(x);
```

### Salida

```text
===== TOKENS =====
Token{type=PALABRA_CLAVE, value='int'}
Token{type=IDENTIFICADOR, value='x'}
Token{type=DELIMITADOR, value=';'}
Token{type=IDENTIFICADOR, value='x'}
Token{type=OPERADOR, value='='}
Token{type=LITERAL_NUMERICO, value='5'}
Token{type=DELIMITADOR, value=';'}
Token{type=PALABRA_CLAVE, value='print'}
Token{type=DELIMITADOR, value='('}
Token{type=IDENTIFICADOR, value='x'}
Token{type=DELIMITADOR, value=')'}
Token{type=DELIMITADOR, value=';'}

Analisis sintactico correcto
Analisis semantico finalizado
```

## Manejo de errores

### Error léxico

Entrada:

```java
@
```

Salida:

```text
Error léxico: símbolo desconocido -> @
```

### Error sintáctico

Entrada:

```java
x = ;
```

Salida:

```text
Error sintáctico: Token inesperado
```

### Error semántico

Entrada:

```java
print(y);
```

Salida:

```text
Error: variable no declarada -> y
```

## Cómo ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/TU_USUARIO/mini-compilador-java.git
```

2. Abrir el proyecto en Visual Studio Code.

3. Asegurarse de tener instalado Java 11 o superior.

4. Ejecutar la clase `Main.java`.

5. Introducir el código por consola y finalizar la entrada escribiendo:

```text
FIN
```

## Posibles mejoras futuras

* Generación de árbol sintáctico.
* Soporte para nuevos tipos de datos.
* Inclusión de estructuras de control (`if`, `while`).
* Lectura de código desde archivos.
* Interfaz gráfica para la introducción del código.

## Autores

Proyecto desarrollado por Daniel como práctica académica de la asignatura de Lenguajes y Compiladores.

## Licencia

Este proyecto tiene fines exclusivamente educativos y académicos.
