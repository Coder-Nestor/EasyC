#include <iostream>
#include <string>
using namespace std;

int main() {
    int numero;
    cout << "Ingrese un numero: " << endl;
    cin >> numero;
    if ((numero > 0)) {
        cout << "El numero es positivo" << endl;
} else {
        if ((numero < 0)) {
        cout << "El numero es negativo" << endl;
} else {
        cout << "El numero es cero" << endl;
}
}
    
    return 0;
}
