inicio {
    entero numero;
    
    imprimir("Ingrese un numero: ");
    leer(numero);
    
    si (numero > 0) {
        imprimir("El numero es positivo");
    } sino {
        si (numero < 0) {
            imprimir("El numero es negativo");
        } sino {
            imprimir("El numero es cero");
        }
    }
}
fin