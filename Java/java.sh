#!/bin/bash

# Ruta de ANTLR (corrige la ruta si es necesario)
ANTLR_JAR="$HOME/Downloads/ANTLR-main/antlr-4.13.1-complete.jar"
GRAMMAR_FILE="LabeledExpr.g4"

# Generar archivos con ANTLR
echo "Generando archivos con ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Java -no-listener -visitor "$GRAMMAR_FILE"

# Verificar si la generación de archivos fue exitosa
if [ $? -ne 0 ]; then
    echo "Error al generar archivos con ANTLR."
    exit 1
fi

# Compilar los archivos generados y Calc.java
echo "Compilando archivos Java..."
javac -cp ".:$ANTLR_JAR" Calc.java LabeledExpr*.java

# Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
    echo "Error en la compilación."
    exit 1
fi

# Ejecutar la calculadora
echo "Ejecutando la calculadora..."
java -cp ".:$ANTLR_JAR" Calc
