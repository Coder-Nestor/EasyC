#include <iostream>
#include <string>
using namespace std;

int multiplicar(int a, int b) {
        return (a * b);
}


int main() {
    int resultado = multiplicar(4, 5);
    cout << "Resultado:" << endl;
    cout << resultado << endl;
    
    return 0;
}
