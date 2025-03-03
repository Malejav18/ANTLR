#!/bin/bash

# Ruta de ANTLR
ANTLR_JAR="$HOME/Downloads/ANTLR-main/antlr-4.13.1-complete.jar"
GRAMMAR_FILE="LabeledExpr.g4"

echo "Instalando python con antlr..."
sudo apt install python3-pip
sudo pip3 install antlr4-python3-runtime --break-system-packages

# Generar archivos con ANTLR
echo "Generando archivos con ANTLR..."
java -jar "$ANTLR_JAR" -Dlanguage=Python3 -visitor "$GRAMMAR_FILE"

# Ejecutar la calculadora
echo "Ejecutando la calculadora..."
python3 Calc.py

