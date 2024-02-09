import 'package:flutter/material.dart';

void main() {
  //===============================Exemplo 1===============================
  //Declarando variaveis
  String nome = "João";
  int idade = 25;
  double altura = 1.75;
  bool estudante = true;

  //Imprimir variaveis
  print("Nome: $nome");
  print("Idade: $idade anos");
  print("Altura: $altura metros");
  print("Estudante: $estudante");

  //===============================Exemplo 2===============================
  // Operações aritméticas com variáveis
  int numero1 = 10;
  int numero2 = 5;

  int soma = numero1 + numero2;
  int subtracao = numero1 - numero2;
  int multiplicacao = numero1 * numero2;
  double divisao = numero1 / numero2;

// Imprimindo resultados
// Imprimindo resultados
  print("Soma: $soma");
  print("Subtração: $subtracao");
  print("Multiplicação: $multiplicacao");
  print("Divisão: $divisao");

  //===============================Exemplo 3===============================
  // Concatenação de strings com variáveis
  String nome3 = "Maria";
  int idade3 = 30;

// Utilizando concatenação
  String mensagem = "Olá, meu nome é $nome3 e tenho $idade3 anos.";

// Imprimindo a mensagem
  print(mensagem);


  //===============================Exemplo 4===============================
  // Variáveis mutáveis
var numero = 42;
print("Número inicial: $numero");

// Alterando o valor da variável
numero = 100;
print("Número alterado: $numero");

// Variável de tipo dinâmico
dynamic variavelDinamica = "Texto";
print("Variável dinâmica: $variavelDinamica");

// Alterando dinamicamente o tipo e valor
variavelDinamica = 42;
print("Variável dinâmica alterada: $variavelDinamica");
}
