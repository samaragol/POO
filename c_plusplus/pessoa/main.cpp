#include <iostream>
#include <string>
#include "pessoa.hpp"

using namespace std;

void imprimePessoa(const Pessoa& p)
{
    // passagem por referência (mais eficiente), 
    // mas com garantia de não haver alteração do estado de p
    cout << p.getNome() << endl;
}

int main()
{
    Pessoa p1 = {"Sicrano",19}; // Pessoa p1("Sicrano",19) é outra forma com mesmo resultado
    Pessoa p2=p1;               // construtor de cópia implícito é chamado
    Pessoa p3 = {"Fulano"};     // idade 18 default é usada
    imprimePessoa(p1);
    imprimePessoa(p2);
    p2.setNome("Sicrano copiado");
    imprimePessoa(p1);
    imprimePessoa(p2);
}

