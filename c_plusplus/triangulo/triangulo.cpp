#include "triangulo.hpp"

#include <cmath>
Triangulo::Triangulo(Ponto pA, Ponto pB, Ponto pC): pA(pA), pB(pB), pC(pC) {}

float Triangulo::getPerimetro() {
    float ladoA = hypot(pA.getX() - pB.getX(), pA.getY() - pB.getY());
    float ladoB = hypot(pB.getX() - pC.getX(), pB.getY() - pC.getY());
    float ladoC = hypot(pC.getX() - pA.getX(), pC.getY() - pA.getY());

    return ladoA + ladoB + ladoC;
}