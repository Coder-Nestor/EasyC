#include <iostream>
#include <string>
using namespace std;

class Persona {
private:
    string nombre;
    int edad;

public:
    void setNombre(string n) {
        nombre = n;
    }

    void setEdad(int e) {
        edad = e;
    }

    void mostrarDatos() {
        cout << nombre << endl;
        cout << edad << endl;
    }

};


int main() {
    Persona p1;
    p1.setNombre("Juan");
    p1.setEdad(25);
    p1.mostrarDatos();
    
    return 0;
}
