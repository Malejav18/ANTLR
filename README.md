# ANTLR

Integrantes:

- Eduardo Hincapie 
- Josh Lopez 
- Miguel Suarez 
- Alejandra Vargas


# 🧷 Requerimientos necesarios

Para correr el programa creado se necesita tener instalado **ANTLR**, el cual esta disponible en sistemas Linux / macOs.

### Proceso de instalación de ANTLR: 

### Linux
```
sudo apt install 
```



### macOs
Instalar homebrew
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Instalar ANTLR
```
brew install gnuplot
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
### Ejectutar el progrma con t.expr
```
java Calc t.expr
```
Opcionalmente se puede ver las expresiones de t.expr usando
```
cat t.expr
```

# ⚙Pruebas funcionales

Entrada  
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

Salida
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
### Casos especiales
Para los siguientes casos el programa mostrara un mensaje de error y se terminara prematuramente:
- Division por cero
- Variables sin asignar
- Mas de dos simbolos de operacion seguidos (exceptuando el simbolo "-")
