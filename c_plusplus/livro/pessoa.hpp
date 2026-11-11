#if !defined(PESSOA_HPP)
#define PESSOA_HPP

#include <string>

using namespace std;

class Pessoa
{
    string nome;
    int idade;
public:
    Pessoa(const string& nome, int idade=18);
    void setNome(const string& nome);
    string getNome() const;
};

#endif // PESSOA_HPP
