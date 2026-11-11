#include <iostream>

#include "triangulo.hpp"

int main() {
    Ponto pA(1, 4);
    Ponto pB(1, 0);
    Ponto pC(4, 0);

    Triangulo t(pA, pB, pC);

    std::cout << "O perímetro do triângulo é " << t.getPerimetro() << std::endl;
}