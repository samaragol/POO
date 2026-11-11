#include <iostream>

#include "ponto.hpp"

void imprimePonto(const Ponto &p)
{
    std::cout << "Ponto no plano com coordenadas " << p.getX() << ", " << p.getY() << std::endl;
}
int main()
{
    Ponto p(1,2);
    Ponto origem(0,0);
    imprimePonto(p);
    imprimePonto(origem);
    imprimePonto(p);
    imprimePonto(origem);
}
