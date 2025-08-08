#!/bin/bash

# ==== Configuración ====
ANTLR_JAR="antlr-4.13.1-complete.jar"
SRC_DIR="."
OUTPUT_CPP="programa.cpp"
OUTPUT_EXE="programa.out"

# ==== Verificar argumentos ====
if [ -z "$1" ]; then
    echo "Uso: run_easy archivo.ec"
    exit 1
fi

# ==== Ejecutar traductor ====
echo "[1/3] Generando C++ desde $1 ..."
java -cp "$ANTLR_JAR:$SRC_DIR" Main "$1"

# ==== Compilar C++ ====
echo "[2/3] Compilando $OUTPUT_CPP ..."
g++ "$OUTPUT_CPP" -o "$OUTPUT_EXE"
if [ $? -ne 0 ]; then
    echo "Error de compilación."
    exit 1
fi

# ==== Ejecutar programa ====
echo "[3/3] Ejecutando $OUTPUT_EXE ..."
./"$OUTPUT_EXE"
