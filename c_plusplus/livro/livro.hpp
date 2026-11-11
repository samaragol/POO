#if !defined(LIVRO_H)
#define LIVRO_H

#include "pessoa.hpp"

class Livro{
    Pessoa *autor;
    string titulo;

public:
    Livro(Pessoa &autor, string titulo);
    void imprimeLivro();
};

#endif