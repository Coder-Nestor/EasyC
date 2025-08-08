#include <iostream>
#include <string>
using namespace std;

class Persona {
private:
    string nombre;
    int edad;

public:
    void colocarNombre(string n) {
        nombre = n;
    }

    string obtnerNombre() {
        return nombre;
    }

    void colocarEdad(int e) {
        edad = e;
    }

    void saludar() {
        cout << "Hola, soy " << endl;
        cout << nombre << endl;
        cout << " y tengo " << endl;
        cout << edad << endl;
        cout << " anios" << endl;
    }

};


Persona persona1;

int main() {
    persona1.colocarNombre("Ana");
    persona1.colocarEdad(25);
    persona1.saludar();
    cout << "El nombre es: " << endl;
    cout << persona1.obtnerNombre() << endl;
    
    return 0;
}
