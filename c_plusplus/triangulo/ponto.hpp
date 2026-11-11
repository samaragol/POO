#if !defined(PONTO_HPP)
#define PONTO_HPP

class Ponto
{
    // por padrão private em uma class
    int x;
    int y;
public:
    Ponto(int x, int y);
    int getX() const;
    int getY() const;
    // setters incluídos apenas para exemplificar controle de mutabilidade
    void setX(int x);   
    void setY(int y);
};

#endif // PONTO_HPP
