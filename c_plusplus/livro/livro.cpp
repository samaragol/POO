#include <iostream>

#include "livro.hpp"

Livro::Livro(Pessoa &autor, string titulo) : autor(&autor), titulo(titulo) {}

void Livro::imprimeLivro() {
    std::cout << "Título do livro: " << titulo << " | Autor: " << autor->getNome() << std::endl;
}