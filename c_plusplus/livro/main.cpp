#include "livro.hpp"

int main() {
    Pessoa autor1("Samara Gonçalves", 89);
    Livro livro1(autor1, "Vidas Secas");

    livro1.imprimeLivro();

    autor1.setNome("Graciliano Ramos");

    livro1.imprimeLivro(&skfjksadj);
}