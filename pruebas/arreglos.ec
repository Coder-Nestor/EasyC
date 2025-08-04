entero numeros[5] : {1, 2, 3, 4, 5};

funcion entero sumarArreglo() {
  entero suma : 0;
  entero i : 0;
  mientras (i < 5) {
    suma : suma + numeros[i];
    i : i + 1;
  }
  retornar suma;
}

inicio {
  entero resultado : sumarArreglo();
  imprimir("Suma total:");
  imprimir(resultado);
}
fin