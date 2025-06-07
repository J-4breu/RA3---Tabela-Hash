# Análise de Desempenho de Tabelas Hash em Java

Este repositório contém um projeto acadêmico desenvolvido em Java para implementar e realizar uma análise comparativa do desempenho de diferentes configurações de Tabelas Hash (Hash Tables). O objetivo é observar o impacto de variações no tamanho da tabela, na função de hash utilizada e no volume de dados sobre as métricas de performance, como tempo de inserção, tempo de busca, número de colisões e de comparações.

---

## 🎯 Objetivo do Trabalho

O projeto foi guiado pelos seguintes passos:

1.  **Definir Tamanhos de Tabela:** Escolher três tamanhos de vetor com variação de 10x entre eles (`10.000`, `100.000`, `1.000.000`).
2.  **Escolher Funções de Hash:** Implementar três funções de hash distintas (Resto da Divisão, Multiplicação e Dobramento).
3.  **Gerar Dados:** Criar três conjuntos de dados massivos (`1 milhão`, `5 milhões`, `20 milhões` de registros) de forma aleatória, porém reprodutível, utilizando `seeds`.
4.  **Medir Inserção:** Inserir os dados em cada combinação de tabela/função, medindo o tempo de inserção e o número total de colisões.
5.  **Medir Busca:** Realizar um conjunto de buscas para cada configuração, medindo o tempo de busca e o número de comparações necessárias.
6.  **Apresentar Resultados:** Organizar os dados coletados para permitir a criação de gráficos e uma análise comparativa detalhada.

---

## 📂 Estrutura do Projeto

O código-fonte está organizado nas seguintes classes Java:

-   `AnalisadorHash.java`: Classe principal (`main`) que orquestra todo o experimento. Ela é responsável por criar as tabelas, gerar os dados, executar os testes e imprimir os resultados no console em formato CSV.
-   `TabelaHash.java`: A implementação da Tabela Hash em si. Utiliza a estratégia de **Encadeamento Separado (Separate Chaining)** com `java.util.LinkedList` para tratar as colisões.
-   `Registro.java`: Representa o objeto de dado que é armazenado na tabela, contendo um código inteiro de 9 dígitos.
-   `FuncaoHash.java`: Uma interface que define o contrato para qualquer função de hash.
-   `HashDivisao.java`, `HashMultiplicacao.java`, `HashDobramento.java`: Implementações concretas da interface `FuncaoHash`, cada uma com sua lógica de cálculo de hash.

---

## 🛠️ Como Executar o Projeto

Para compilar e executar este projeto, você precisará ter o **Java Development Kit (JDK)** (versão 8 ou superior) instalado em sua máquina.

**1. Clone o Repositório**
```bash
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
cd seu-repositorio
```

**2. Compile os Arquivos Java**
Abra um terminal ou prompt de comando na pasta do projeto e execute o seguinte comando para compilar todos os arquivos `.java`:
```bash
javac *.java
```

**3. Execute a Análise e Salve os Resultados**
Execute a classe principal. É altamente recomendável redirecionar a saída para um arquivo `.csv`, que pode ser facilmente aberto em qualquer software de planilhas (Excel, Google Sheets, etc.).
```bash
java AnalisadorHash > resultados.csv
```
A execução pode levar alguns minutos devido ao grande volume de dados. Ao final, você terá um arquivo chamado `resultados.csv` na pasta do projeto com todos os dados do experimento.

---

## 📊 Análise e Conclusões

A análise dos dados coletados em `resultados.csv` revelou os seguintes pontos chave:

### 1. O Impacto do Fator de Carga
O fator de carga (`α = Número de Dados / Tamanho da Tabela`) é o indicador mais crítico de desempenho. Como observado no gráfico de "Comparações vs. Fator de Carga", há uma correlação direta e acentuada: quanto maior o fator de carga, maior o número de comparações e, consequentemente, pior o tempo de busca.

![image](https://github.com/user-attachments/assets/b9895f0d-fc39-446b-b4ea-e0f1ce55a389)
*(Exemplo de gráfico gerado a partir dos dados)*

### 2. Comparativo das Funções de Hash
-   **Divisão e Multiplicação:** Ambas as funções se mostraram robustas e eficientes, com um número de colisões muito similar entre si e próximo do valor teórico esperado.
-   **Dobramento:** A implementação específica de dobramento (`HashDobramento`) se mostrou **menos eficaz** que as outras, gerando um número de colisões significativamente maior. Isso ocorreu porque a soma das partes da chave gerava valores em uma faixa restrita, não aproveitando todo o espaço de endereçamento das tabelas maiores.

![image](https://github.com/user-attachments/assets/c40e3410-08c6-4142-8d0e-4e5363606c79)
*(Exemplo de gráfico gerado a partir dos dados)*

### 3. A Importância do Tamanho da Tabela
Aumentar o tamanho da tabela, diminuindo o fator de carga, tem um impacto drástico e positivo na performance de busca. Os testes mostraram que, para um mesmo volume de dados, uma tabela 10x maior pode reduzir o tempo de busca e o número de comparações em mais de 90%.
![image](https://github.com/user-attachments/assets/fc9ed5cd-2acf-426b-80e7-bf2a6cf9356a)

---
