

/# Ejemplo simple de POO - Clase Persona #/

clase Persona {
    cadena nombre;
    entero edad;
    
    metodo void colocarNombre(cadena n) {
        nombre : n;
    }
    
    metodo cadena obtnerNombre() {
        retornar nombre;
    }
    
    metodo void colocarEdad(entero e) {
        edad : e;
    }
    
    metodo void saludar() {
        imprimir("Hola, soy ");
        imprimir(nombre);
        imprimir(" y tengo ");
        imprimir(edad);
        imprimir(" anios");
    }
}

/# Variable global #/
Persona persona1;

inicio {
    /# Configurar persona #/
    persona1.colocarNombre("Ana");
    persona1.colocarEdad(25);
    
    /# Mostrar saludo #/
    persona1.saludar();
    
    /# Mostrar nombre directamente #/
    imprimir("El nombre es: ");
    imprimir(persona1.obtnerNombre());
}
fin