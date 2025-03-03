#!/bin/bash

ANTLR_JAR="$HOME/Downloads/ANTLR-main/antlr-4.13.1-complete.jar"
GRAMMAR_FILE="LabeledExpr.g4"

echo "Generando archivos con ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Java -no-listener -visitor "$GRAMMAR_FILE"

if [ $? -ne 0 ]; then
    echo "Error al generar archivos con ANTLR."
    exit 1
fi

echo "Compilando archivos Java..."
javac -cp ".:$ANTLR_JAR" Calc.java LabeledExpr*.java

if [ $? -ne 0 ]; then
    echo "Error en la compilación."
    exit 1
fi

echo "Ejecutando la calculadora..."
java -cp ".:$ANTLR_JAR" Calc
