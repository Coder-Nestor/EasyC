#include <iostream>
#include <string>
using namespace std;

int numeros[5];

int obtenerElemento(int i) {
        return numeros[i];
}


int sumar() {
        int i = 0;
        int suma = 0;
        while ((i <= 5)) {
        suma = (suma + obtenerElemento(i));
        i = (i + 1);
}
        return suma;
}


int main() {
    numeros[0] = 1;
    numeros[1] = 2;
    numeros[2] = 3;
    numeros[3] = 4;
    numeros[4] = 5;
    int resultado = sumar();
    cout << "Suma total:" << endl;
    cout << resultado << endl;
    
    return 0;
}
