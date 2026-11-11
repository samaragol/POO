#include "pessoa.hpp"

#include <iostream>

Pessoa::Pessoa(const string& nome, int idade) : nome(nome), idade(idade) 
{
    std::cout << "Construímos uma pessoa de nome " << nome << " e idade " << idade << std::endl;
};

void Pessoa::setNome(const string& nome)
{
    this->nome=nome;
}

string Pessoa::getNome() const
{
    return nome;
}
