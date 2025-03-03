#!/bin/bash

# Ruta de ANTLR
ANTLR_JAR="/Downloads/ANTLRBackup-main/antlr-4.13.1-complete.jar"
GRAMMAR_FILE="LabeledExpr.g4"

# Generar archivos con ANTLR
echo "Generando archivos con ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Python3 -visitor "$GRAMMAR_FILE"

# Ejecutar la calculadora
echo "Ejecutando la calculadora..."
python3 Calc.py

