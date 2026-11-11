#include <iostream>
#include "ponto.hpp"

Ponto::Ponto(int x, int y) : x(x), y(y) { 
    std::cout << "Ponto criado" << std::endl;
}

int Ponto::getX() const { 
    return this->x; 
}

int Ponto::getY() const { 
    return this->y; 
}

void Ponto::setX(int x)
{
    this->x=x;
}

void Ponto::setY(int y)
{
    this->y=y;
}