# 📚 ANTLR - Calculadora con Manejo de Errores

## Integrantes

- Eduardo Hincapie
- Josh Lopez
- Miguel Suarez
- Alejandra Vargas

---

## 📝 Descripción

Este proyecto implementa una calculadora aritmética utilizando **ANTLR (Another Tool for Language Recognition)** para el análisis léxico y sintáctico. La calculadora es capaz de evaluar expresiones matemáticas, asignar y utilizar variables, y manejar errores comunes de sintaxis y ejecución de manera controlada.


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

## Ejemplo de Entrada  
```    
x = -5.32      
y = 3+*9      
3.1 *(-x+2)      
.4+3      
52.3//2.1      
7**1      
6++3      
3--2      
y/y+10      
(3*+1)/*6      
12.-1-4/4      
-3*8      
"
4/0
```

## Ejemplo de Salida
```
line 2:6 extraneous input '*' expecting {'(', '-', ID, FLOAT}
line 5:5 extraneous input '/' expecting {'(', '-', ID, FLOAT}
line 6:2 extraneous input '*' expecting {'(', '-', ID, FLOAT}
line 7:2 extraneous input '+' expecting {'(', '-', ID, FLOAT}
line 10:3 extraneous input '+' expecting {'(', '-', ID, FLOAT}
line 10:7 extraneous input '*' expecting {'(', '-', ID, FLOAT}
line 13:0 token recognition error at: '"'
22.692
3.4
24.904762
7.0
9.0
5.0
11.0
0.5
10.0
-24.0
se detecto una division por 0
```
El parser con el que se trabaja permite ignorar errores, como simbolos fuera del lenguaje, y mostrar un aviso. Cuando hay dos simbolos de operacion (+,-,*,/) seguidos solo tendra en cuenta el primero, con excepción  de la resta ya que esto se interpretara como parte de un numero negativo. En caso de haber un un punto sin que le siga un numero se ignorara.

## 🚨 Casos Especiales

En las siguientes situaciones, el programa mostrará un mensaje de error y se detendrá de manera controlada:

- División por cero.
- Uso de variables no asignadas.
- Más de dos símbolos de operación consecutivos (excepto el signo "-").

---





