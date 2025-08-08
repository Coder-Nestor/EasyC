@echo off
REM ==== Configuración ====
set ANTLR_JAR=antlr-4.13.1-complete.jar
set SRC_DIR=.
set OUTPUT_CPP=programa.cpp
set OUTPUT_EXE=programa.exe

REM ==== Verificar argumentos ====
if "%~1"=="" (
    echo Uso: run_easy archivo.ec
    exit /b 1
)

REM ==== Ejecutar traductor ====
echo [1/3] Generando C++ desde %~1 ...
java -cp "%ANTLR_JAR%;%SRC_DIR%" Main %~1

REM ==== Compilar C++ ====
echo [2/3] Compilando %OUTPUT_CPP% ...
g++ "%OUTPUT_CPP%" -o "%OUTPUT_EXE%"
if errorlevel 1 (
    echo Error de compilación.
    exit /b 1
)

REM ==== Ejecutar programa ====
echo [3/3] Ejecutando %OUTPUT_EXE% ...
"%OUTPUT_EXE%"
