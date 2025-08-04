inicio {
    entero opcion;
    
    /# Mostrar opciones #/
    imprimir("1. Opción Uno");
    imprimir("2. Opción Dos");
    imprimir("3. Opción Tres");
    imprimir("Ingrese su opción:");
    
    /# Leer opción #/
    leer(opcion);
    
    /# Menú con casos #/
    seleccionar (opcion) {
        caso 1 = 
            imprimir("Hola Mundo!!!");
            romper;
            
        caso 2 = 
            imprimir("Has aprobado compiladores");
            romper;
            
        caso 3 = 
            imprimir("adios!!");
            romper;
            
        defecto = 
            imprimir("Opción no válida");
    }
}
fin