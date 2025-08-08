#include <iostream>
#include <string>
using namespace std;

int main() {
    int num1;
    int num2;
    int resultado;
    float division;
    cout << "Ingrese el primer número: " << endl;
    cin >> num1;
    cout << "Ingrese el segundo número: " << endl;
    cin >> num2;
    cout << "Resultado" << endl;
    cout << "================================" << endl;
    cout << "El resuldado de la suma es:" << endl;
    cout << (num1 + num2) << endl;
    cout << "---------------------------------" << endl;
    cout << "El resuldado de la resta es:" << endl;
    cout << (num1 - num2) << endl;
    cout << "---------------------------------" << endl;
    cout << "El resuldado de la multiplicacion es:" << endl;
    cout << (num1 * num2) << endl;
    cout << "---------------------------------" << endl;
    cout << "El resuldado de la division es:" << endl;
    cout << (num1 / num2) << endl;
    
    return 0;
}
