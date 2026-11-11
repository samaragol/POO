# Primeiro exemplo em C++

Para compilar:

g++ -c -o pessoa.o pessoa.cpp
g++ -c -o main.o main.cpp

Para linkar os arquivos objeto:

g++ -o main main.o pessoa.o



## Observação 

No Windows, as extensões dos arquivos são diferentes:

g++ -c -o pessoa.obj pessoa.cpp
g++ -c -o main.obj main.cpp
g++ -o main.exe main.obj pessoa.obj

