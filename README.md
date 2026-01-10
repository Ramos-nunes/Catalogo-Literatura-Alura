# 📚 Catálogo de Literatura – Challenge Alura

Catálogo de obras literárias, desenvolvido com Java, Spring Boot, utilizando boas práticas de Organização de Código, Separação em Camadas, Banco de Dados e API, Persistência em Banco de Dados, Filtros Avançados, Estatísticas, Documentação do Projeto e Exportações para CSV.

![Java](https://img.shields.io/badge/Java-17+-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Maven](https://img.shields.io/badge/Maven-Build-yellow)

---

## 🎯 Objetivo do Projeto
Este projeto é o resultado do Challenge Backend Java que implementa um catálogo de livros e autores acessado via console, proposto pela **Alura**. A aplicação consome dados da API Gutendex, realiza a persistência em um banco de dados relacional e oferece ferramentas avançadas de análise e exportação.
O objetivo é proporcionar uma experiência prática com o ecossistema Java moderno, focando em persistência de dados, integração com serviços externos e manipulação de arquivos.

**Criar uma aplicação de catálogo literário**, acessada via console, que permita:
* Buscar livros na API Gutendex pelo título
* Selecionar um livro retornado pela API e salvar localmente
* Consultar livros já cadastrados no banco
* Consultar autores cadastrados
* Listar autores vivos em determinado ano
* Listar livros por idioma
* Gerar estatísticas completas do catálogo
* Exportar dados de livros e autores para arquivos CSV

**O foco principal é praticar:**
* **Consumir dados** de livros via API REST.
* **Armazenar** livros e autores em um banco PostgreSQL.
* **Consultar,filtrar e e explorar** informações diretamente pelo console.
* **Gerar estatísticas** completas sobre o acervo.
* **Exportar Catálogo dados** para arquivos CSV (compatíveis com Excel, BI e bancos externos).
•	**Extrair dados** de livros via API
•	**Manipulação** de JSON  
•	**Persistência** com JPA/Hibernate  
  
---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3**
* **Maven** (Gerenciamento de dependências)
* **Spring Data JPA**
* **PostgreSQL**
* **API Gutendex** (Fonte de dados externa)
* **Jackson** (Manipulação de JSON)
* **IntelliJ IDEA**
* **CSV para exportação de dados**

---

## 🗂️ Estrutura do Projeto
```       
src/
 └── main/
     ├── java/
     │   └── literatura/
     │        ├── model/      # Entidades (Livro, Autor)
     │        │     ├── Livro.java
     │        │     └── Autor.java
     │        │
     │        ├── repository/          # Interfaces de acesso ao banco
     │        │     ├── LivroRepository.java
     │        │     └── AutorRepository.java
     │        │
     │        ├── service/          # Lógica de negócio e exportação
     │        │     ├── LivroService.java
     │        │     └── AutorService.java
     │        │
     │        ├── client/             # Integração com a API (HTTP Client)
     │        │     └── GutendexClient.java
     │        │
     │        └── LiteraturaCatalogoApplication.java
     │
     └── resources/
           ├── application.properties     # Configurações do sistema
           └── static/ (CSV exportados, se desejado)    # Local de saída dos arquivos CSV

```

## ▶️ Como Executar o Projeto
  
**1. Pré-requisitos:**
* Java JDK 17+
* Maven
* PostgreSQL (ou outro banco relacional configurado)
* IntelliJ IDEA (opcional, mas recomendado)
* Caso contrário realize a instalação de todos.

**2. Clonar o repositório:** git clone https://github.com/Ramos-nunes/Catalogo-Literatura-Alura
  
**3. Entrar na pasta do projeto:** cd Catalogo-Literatura-Alura/literaturaCatalogo

**4. Configure o banco de dados:** Ajuste as credenciais e URL do banco no arquivo application.properties (ou application.yml), conforme sua instalação do PostgreSQL.
  
**5. Executar o projeto:** mvn spring-boot:run

**6. Interagir pelo console:** O menu principal será exibido no terminal, permitindo escolher as opções numéricas.

---

## ⚙️ Configuração do Banco de Dados
No arquivo `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Recomendamos usar o **pgAdmin** para visualizar o banco.

---

## 🌐 Consumo da API

Os livros são obtidos da API pública **Gutendex**:

https://gutendex.com/books/

A aplicação realiza:

- requisições HTTP  
- análise de JSON  
- conversão para objetos Java  
- persistência automática no banco  

---

## 🧭 Menu Principal - Navegação, Interface e Funcionalidades
O **Menu Principal** foi projetado para ser intuitivo e oferecer uma navegação completa pelo acervo. Ele atua como o ponto central da aplicação, onde o usuário pode interagir com os dados vindos da API e persistidos no PostgreSQL.

#### O menu permite realizar:
* **Gestão de Conteúdo:** Busca e listagem de livros e autores em tempo real.
* **Filtros Históricos:** Consulta avançada de autores com base em anos de nascimento, falecimento e período de atividade.
* **Inteligência de Dados:** Ranking dos livros mais populares e estatísticas detalhadas do banco de dados.
* **Interoperabilidade:** Exportação de todos os dados cadastrados para o formato CSV. 
```
==============================
      CATALOGO DE LITERATURA
==============================

[ CONSULTAS BÁSICAS ]
  1 - Buscar livro por titulo
  2 - Listar cadastrados
  3 - Listar autores cadastrados

[ CONSULTAS AVANÇADAS ]
  4 - Autores vivos em determinado ano
  5 - Listar livros por idioma
  6 - Buscar autor por nome
  8 - Autores nascidos antes de um ano
  9 - Autores falecidos depois de um ano
  10 - Autores vivos entre dois anos
  11 - Autores sem falecimento (vivos hoje)
  12 - Autores mortos antes de um ano

[ RANKING & ESTATÍSTICAS ]
  7 - Top 10 livros mais baixados
  13 - Estatísticas gerais (Livros e Autores)

[ FERRAMENTAS ]
  14 - Exportar catalogo para CSV

[ SISTEMA ]
  0 - Sair

```
Todas as operações são validadas e o sistema evita duplicações de livros e autores já cadastrados.

---

## 📊 Estatísticas (Opção 13)
O sistema gera dados detalhados como:

* **Total de livros cadastrados**
* **Total de autores cadastrados**
* **Total de livros por idiomas**  
* **Ranking dos livros mais baixados**  
* **Ranking dos livros menos baixados**  
* **Total geral de downloads**  
* **Total de autor com mais livros**  
* **Idiomas mais frequentes**
  
Essas estatísticas demonstram o uso prático de Streams, consultas derivadas e agregações no Backend.  
As estatísticas foram pensadas para entregar uma visão completa do **acervo API pública Gutendex.**  
Saída completa, detalhada e pronta para gerar relatórios.

---

## 📤 Exportação para CSV (Opção 14)

São gerados:

- `Catálogo completo de Livros`  
- `Catálogo completo de Autores`

Os arquivos CSV podem ser utilizados em:
* UTF-8  
* Seguro para Excel/LibreOffice
* Ferramentas de BI (Power BI, etc.)
* Scripts Python/Pandas
* R, SQL, e PostgreSQL e outros ambientes de Análise de dados
* Campos limpos (sem quebras de linha ou caracteres inválidos)

---

## 📘 Estrutura dos Arquivos CSV

- `catalogo_livros.csv`

id;titulo;idioma;downloads;autor_id;autor_nome  
1;Dom Casmurro;pt;1238;1;Machado de Assis  
2;Wit and Wisdom of Don Quixote;en;769;2;Cervantes Saavedra, Miguel de   
3;Iracema: com uma noticia biographica do auctor;pt;405;3;Alencar, José Martiniano de  
4;Nova academia de pintura: dedicada às senhoras portuguesas que amam ou se aplicão, ao estudo das Belas Artes;pt;291;4;Machado, Cirilio Volkmar  
5;O Cortiço;pt;380;5;Azevedo, Aluísio  
6;Pride and Prejudice;en;79110;6;Austen, Jane  
7;The Odyssey: Rendered into English prose for those who cannot read the original;en;20996;7;Homer  
8;The Iliad;en;24050;7;Homer  
9;Great Expectations;en;24243;8;Dickens, Charles

- `catalogo_autores.csv`

id;nome;ano_nascimento;ano_falecimento;total_livros  
1;Machado de Assis;1839;1908;1  
2;Cervantes Saavedra;;;;  
3;Alencar;;;;  
4;Machado;;;;  
5;Azevedo;;;;  
6;Austen;;;;  
7;Homer;-750;-650;2  
8;Dickens;;;;

---

## 📄 Challenge Original

O desafio proposto pela Alura nesse desafio inclui:

* Estrutura de aprendizagem (Trello, Discord, etc.)
* Pré‑requisitos de Java, Spring, Banco de Dados
* Descrição da API Gutendex
* Etapas do desafio (consumir API, persistência, consultas, extras)
* Sugestões de funcionalidades adicionais (EXTRAS)
* Orientações para documentação (README)
* Passos para entrega e certificação.

---

## 📚 O que aprendi com este Projeto?
Durante o desenvolvimento deste catálogo de literatura, pude praticar e consolidar:

* Consumo de APIs REST com Java (HttpClient, HttpRequest, HttpResponse)
* Manipulação de JSON com Jackson (ObjectMapper, @JsonAlias, @JsonIgnoreProperties)
* Modelagem de entidades (Livro, Autor)
* Persistência de dados com Spring Data JPA
* Criação de repositórios e consultas derivadas
* Uso de Streams e lambdas para filtrar, mapear e gerar estatísticas
* Exportação de dados para CSV de forma padronizada
* Organização de um projeto Spring Boot
* Uso de Git e GitHub em um fluxo similar ao de uma empresa
* Importância de um README bem escrito para portfólio
* Manipulação de coleções (List, Map, Stream API)
* Encapsulamento e boas práticas
* Padronização de repositórios profissionais
* Organização de projetos Java
* Tratamento de erros e validações

---

## 🏁 Status do Projeto
[x] Consumo da API Gutendex  
[x] Cadastro de livros e autores  
[x] Consultas principais (livros, autores, vivos em ano, idioma)  
[x] Organização do repositório  
[x] Documentação em README  
[x] Estatísticas (básicas e/ou avançadas)  
[x] Exportação para CSV  
Projeto concluído para o Challenge Literatura, com possibilidades abertas para novas melhorias e funcionalidades extras.
A aplicação funciona de ponta a ponta, com dados limpos, estatísticas funcionais e documentação profissional.

---

## 📌 Possíveis Melhorias Futuras neste Projeto

- Filtros avançados adicionais  
- Exportação em JSON  
- Estatísticas gráficas  
- Interface web com Spring MVC  

---

## 📄 Licença

Projeto aberto para estudo e evolução.  
Uso livre para fins educacionais.  
Caso queira trocar ideias sobre Java, Backend ou melhorias no Projeto:  
GitHub: https://github.com/Ramos-nunes
