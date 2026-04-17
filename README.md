# Computação Orientada a Objetos (COO) - Gestão de Produtos

Este repositório contém a implementação de um sistema de gestão e filtragem de produtos desenvolvido para a disciplina de Computação Orientada a Objetos na **EACH-USP**. O projeto foca-se na aplicação prática de **Padrões de Projeto (Design Patterns)** e nos pilares da Programação Orientada a Objetos (POO).

## 💻 Sobre o Projeto

O sistema é capaz de carregar dados de produtos a partir de ficheiros CSV e realizar operações de ordenação dinâmica. O diferencial técnico está na forma como o sistema lida com a formatação visual e a lógica de ordenação, utilizando interfaces para garantir um código extensível e de fácil manutenção.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17+)
* **Paradigma:** Orientação a Objetos
* **Conceitos:** Encapsulamento, Herança, Polimorfismo e Interfaces.

## 🏗️ Padrões de Projeto Implementados

### 1. Strategy Pattern
Utilizado na interface `AlgoritmoOrdenacao`. Este padrão permite que diferentes algoritmos de ordenação sejam trocados em tempo de execução sem alterar a lógica principal do sistema.
* **Critérios implementados:** Ordenação por descrição (Crescente/Decrescente) e por quantidade em stock.

### 2. Decorator Pattern
Implementado no `CarregadorDeProdutos` para a formatação dinâmica dos produtos.
* Em vez de criar múltiplas subclasses rígidas, o sistema "envolve" o objeto base (`ProdutoPadrao`) com decoradores como `ProdutoEmNegrito`, `ProdutoEmItalico` ou `ProdutoCor` com base nos metadados do CSV.

## 📂 Estrutura de Ficheiros

* `AlgoritmoOrdenacao.java`: Interface que define a estratégia de ordenação.
* `CarregadorDeProdutos.java`: Lógica de leitura de ficheiro e aplicação de decoradores.
* `Criterio...java`: Classes que implementam as diferentes regras de comparação.

## 🚀 Como Executar

1. Certifique-se de que tem o Java instalado.
2. Clone o repositório:
   ```bash
   git clone [https://github.com/t-iamamura/COO.git](https://github.com/t-iamamura/COO.git)
