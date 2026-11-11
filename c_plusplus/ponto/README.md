# Ponto no plano cartesiano

O programa cria pontos bidimensionais e imprime suas coordenadas. A classe controla o acesso às coordenadas por meio de getters e setters.

## Conceitos apresentados

- Definição de classe com atributos privados e métodos públicos.
- Inicialização de atributos pela lista de inicialização do construtor.
- Métodos `const` para consultar o estado do objeto sem modificá-lo.
- Passagem de objeto por referência constante (`const Ponto &`).
- Getters e setters como formas controladas de acesso aos atributos.

## Estrutura

- `ponto.hpp`: declaração da classe `Ponto`.
- `ponto.cpp`: implementação do construtor, dos getters e dos setters.
- `main.cpp`: criação de dois pontos e impressão de suas coordenadas.

## Como compilar e executar

É necessário um compilador C++ com suporte a C++17, como o `g++`.

Na pasta `cpp/ponto`, compile e execute com:

```bash
g++ -std=c++17 -Wall -Wextra -Wpedantic -o programa main.cpp ponto.cpp
./programa
```

A saída esperada é:

```text
Ponto criado
Ponto criado
Ponto no plano com coordenadas 1, 2
Ponto no plano com coordenadas 0, 0
Ponto no plano com coordenadas 1, 2
Ponto no plano com coordenadas 0, 0
```

O construtor imprime uma mensagem a cada ponto criado. A função de impressão é chamada duas vezes para cada ponto. Os setters são apresentados na classe, mas não são chamados pelo `main`.
