#if !defined(TRIANGULO_HPP)
#define TRIANGULO_HPP

#include "ponto.hpp"

class Triangulo {
    Ponto pA;
    Ponto pB;
    Ponto pC;

public:
    Triangulo(Ponto pA, Ponto pB, Ponto pC);
    float getPerimetro();
};

#endif