clase Persona {
    cadena nombre;
    entero edad;
    
    metodo obtenerNombre(cadena n) {
        nombre : n;
    }
    
    metodo obtenerEdad(entero e) {
        edad : e;
    }
    
    metodo mostrarDatos() {
        imprimir(nombre);
        imprimir(edad);
    }
}

inicio {
    Persona p1;
    
    p1.obtenerNombre("Juan");
    p1.obtenerEdad(25);
    p1.mostrarDatos();
}
fin