#include <iostream>
#include <string>
using namespace std;

int main() {
    int opcion;
    cout << "1. Opción Uno" << endl;
    cout << "2. Opción Dos" << endl;
    cout << "3. Opción Tres" << endl;
    cout << "Ingrese su opción:" << endl;
    cin >> opcion;
    switch (opcion) {
        case 1:
            cout << "Hola Mundo!!!" << endl;
            break;
        case 2:
            cout << "Has aprobado compiladores" << endl;
            break;
        case 3:
            cout << "adios!!" << endl;
            break;
        default:
            cout << "Opción no válida" << endl;
}
    
    return 0;
}
