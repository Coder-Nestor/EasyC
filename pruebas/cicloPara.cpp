#include <iostream>
#include <string>
using namespace std;

int main() {
    int suma = 0;
    for (int i = 0; (i < 5); i = (i + 1)) {
        suma = (suma + i);
}
    cout << "La suma es:" << endl;
    cout << suma << endl;
    
    return 0;
}
