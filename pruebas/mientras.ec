entero numeros[5];

funcion entero obtenerElemento(entero i) {
    retornar numeros[i];
}

funcion entero sumar() {
    entero i : 0;
    entero suma : 0;
    mientras (i <: 5) {
        suma : suma + obtenerElemento(i);
        i : i + 1;
    }
    retornar suma;
}

inicio {
    numeros[0] : 1;
    numeros[1] : 2;
    numeros[2] : 3;
    numeros[3] : 4;
    numeros[4] : 5;

    entero resultado : sumar();
    imprimir("Suma total:");
    imprimir(resultado);
}
fin
