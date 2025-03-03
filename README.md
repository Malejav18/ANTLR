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
sudo curl -O http://www.antlr.org/download/antlr-4.13.1-complete.jar
export CLASSPATH=”.:/usr/local/lib/antlr-4.13.1-complete.jar:$CLASSPATH” 
alias antlr4=’java -Xmx500M -cp “/usr/local/lib/antlr-4.13.1-complete.jar:$CLASSPATH” org.antlr.v4.Tool’
alias grun=’java org.antlr.v4.gui.TestRig’
```


### macOs
Instalar homebrew
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
Instalar antlr
```sh
brew install antlr
```

##### Opción 2:

```sh
curl -O https://www.antlr.org/download/antlr-4.13.1-complete.jar
```

Mover el directorio:
```
mv antlr-4.13.1-complete.jar /usr/local/lib/
```

Agregarlo a `PATH` y `CLASSPATH`.
```
nano ~/.zshrc
export CLASSPATH=".:/usr/local/lib/antlr-4.13.1-complete.jar:$CLASSPATH"
alias antlr4='java -jar /usr/local/lib/antlr-4.13.1-complete.jar'
alias grun='java org.antlr.v4.gui.TestRig'
```

Recarga el perfil:
```
source ~/.zshrc
```


# ⚡Como usarlo

## Utilizar shell script

Descargar el zip en descargas
Dar permisos al sh (ejemplo java.sh)
```
chmod +x java.sh
```

Ejecutar el sh
```
./java.sh
```

## Calculadora en java

### Crear el parser
```
antlr4 -no-listener -visitor LabeledExpr.g4
```
### Compilar el programa
```
javac Calc.java LabeledExpr*.java
```
### Ejecutar el programa
```
java Calc
```
Opcionalmente se puede editar y ver las expresiones del caso de prueba t.expr usando
```
java Calc t.expr

cat t.expr
```

## Calculadora en python

Instalar Python con antlr en macOs
```
brew install antlr4-python3-runtime
```

Instalar Python con antlr en Linux
```
sudo apt install python3-pip
sudo pip3 install antlr4-python3-runtime --break-system-packages
```


Crear el parser
```
antlr4 -Dlanguage=Python3 -visitor LabeledExpr.g4
```

Correr la función principal
```
python3 Calc.py
```

### Posibles errores

ImportError: cannot import name 'Lexer' from 'antlr4' (unknown location)
```
python3 -m pip install antlr4-python3-runtime
```

# ⚙ Pruebas funcionales

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

## Funcionamiento normal y División por Cero - t2.expr

### Ejemplo de Entrada  
```    
2 + 3
2 - -5
3*9
4/18
100000**2
2**(-1/2)
| 2 - 3 |
4/0
```

### Ejemplo de Salida
```
5.0
7.0
27.0
0.22222222
1.0E12
1.0
0.70710677
Error: Division por 0
```

El parser con el que se trabaja permite ignorar errores, como simbolos fuera del lenguaje, y mostrar un aviso. Cuando hay dos simbolos de operacion (+,-,*,/) seguidos solo tendra en cuenta el primero, con excepción  de la resta ya que esto se interpretara como parte de un numero negativo. En caso de haber un un punto sin que le siga un numero se ignorara.

## 🚨 Casos Especiales

En las siguientes situaciones, el programa mostrará un mensaje de error correspondiente y se detendrá el funcionamiento del programa:

- División por cero.
- Uso de variables no asignadas.
- Errores sintácticos
- Más de dos símbolos de operación consecutivos (excepto el signo "-").

---





