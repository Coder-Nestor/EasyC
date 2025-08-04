#include <iostream>
#include <string>
using namespace std;

int numeros[5] = {1,2,3,4,5};

int sumarArreglo() {
        int suma = 0;
        int i = 0;
        while ((i < 5)) {
        suma = (suma + numeros[i]);
        i = (i + 1);
}
        return suma;
}


int main() {
    int resultado = sumarArreglo();
    cout << "Suma total:" << endl;
    cout << resultado << endl;
    
    return 0;
}
