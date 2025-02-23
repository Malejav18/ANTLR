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


