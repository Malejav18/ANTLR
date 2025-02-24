# 📚 ANTLR - Calculadora con Manejo de Errores

## Integrantes

- Eduardo Hincapie
- Josh Lopez
- Miguel Suarez
- Alejandra Vargas

---

## 📝 Descripción

Este proyecto implementa una calculadora aritmética utilizando **ANTLR (Another Tool for Language Recognition)** para el análisis léxico y sintáctico. La calculadora es capaz de evaluar expresiones matemáticas (suma, resta, multiplicación y división), asignar y utilizar variables, y manejar errores comunes de sintaxis y ejecución de manera controlada.


## 🧷 Requerimientos

### Dependencias necesarias

- **Java JDK** (versión 8 o superior)
- **ANTLR** (instalable en Linux y macOS)

### Instalación de Java

#### Linux

```sh
sudo apt update
sudo apt install default-jdk
```

#### macOS

```sh
brew install openjdk
```

### Instalación de ANTLR

#### Linux

##### Opción 1:

```sh
sudo apt-get install antlr4
```

##### Opción 2:

```sh
cd /usr/local/lib
sudo curl -O http://www.antlr.org/download/antlr-4.5-complete.jar
export CLASSPATH=”.:/usr/local/lib/antlr-4.5-complete.jar:$CLASSPATH” 
alias antlr4=’java -Xmx500M -cp “/usr/local/lib/antlr-4.5-complete.jar:$CLASSPATH” org.antlr.v4.Tool’
alias grun=’java org.antlr.v4.gui.TestRig’
```


### macOs
Instalar homebrew
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

```sh
brew install antlr
```



# ⚡Como usarlo

### Crear el parser
```
antlr4 -no-listener -visitor LabeledExpr.g4
```
### Compilar el programa
```
javac Calc.java LabeledExpr*.java
```
### Ejecutar el progrma con t.expr
```
java Calc t.expr
```
Opcionalmente se puede ver las expresiones de t.expr usando
```
cat t.expr
```

# ⚙Pruebas funcionales

## Funcionamiento normal - t.expr

### Ejemplo de Entrada  
```    
x = -5.32
3.1 *(-x+2)
.4+3
52.3/2.1
7*0
3--2
12.-1-4/4
-3*8
```

### Ejemplo de Salida
```
22.692
3.4
24.904762
0.0
5.0
10.0
-24.0
```

## Manejo de errores - errores1.expr

### Ejemplo de Entrada  
```    
y = 3+*9
52.3//2.1
7**1
6++3
```

### Ejemplo de Salida
```
line 1:6 no viable alternative at input '*'
line 2:5 no viable alternative at input '/'
line 3:2 no viable alternative at input '*'
line 4:2 no viable alternative at input '+'
Error en la operación suma/resta: Expresión de suma o resta inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la asignación: Asignación inválida para la variable: y
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error al imprimir: Expresión inválida en la impresión
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error al imprimir: Expresión inválida en la impresión
Error en la operación suma/resta: Expresión de suma o resta inválida
Error en la operación suma/resta: Expresión de suma o resta inválida
Error al imprimir: Expresión inválida en la impresión
```

## Manejo de errores - errores1.expr

### Ejemplo de Entrada  
```    
y = 3+*9
y/y+10
(3*+1)/*6
4/0
(
```

### Ejemplo de Salida
```
line 1:6 no viable alternative at input '*'
line 3:3 no viable alternative at input '+'
line 3:7 no viable alternative at input '*'
line 5:1 no viable alternative at input '\n'
Error en la operación suma/resta: Expresión de suma o resta inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la asignación: Asignación inválida para la variable: y
Variable desconocida: y
Variable desconocida: y
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la operación suma/resta: Expresión de suma o resta inválida
Error al imprimir: Expresión inválida en la impresión
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la operación suma/resta: Expresión de suma o resta inválida
Error en la expresión con paréntesis: Paréntesis mal balanceados o expresión inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error en la operación multiplicación/división: Expresión de multiplicación o división inválida
Error al imprimir: Expresión inválida en la impresión
Error en la operación multiplicación/división: División por 0
Error al imprimir: Expresión inválida en la impresión
Error en la expresión con paréntesis: Paréntesis mal balanceados o expresión inválida
Error al imprimir: Expresión inválida en la impresión
```

El parser con el que se trabaja permite ignorar errores, como simbolos fuera del lenguaje, y mostrar un aviso. Cuando hay dos simbolos de operacion (+,-,*,/) seguidos solo tendra en cuenta el primero, con excepción  de la resta ya que esto se interpretara como parte de un numero negativo. En caso de haber un un punto sin que le siga un numero se ignorara.

## 🚨 Casos Especiales

En las siguientes situaciones, el programa mostrará un mensaje de error correspondiente:

- División por cero.
- Uso de variables no asignadas.
- Errores sintácticos
- Más de dos símbolos de operación consecutivos (excepto el signo "-").

---





