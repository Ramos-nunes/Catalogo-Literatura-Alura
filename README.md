# 📚 Catálogo de Literatura – Challenge Alura

Catálogo de obras literárias, desenvolvido com Java, Spring Boot, Banco de Dados e API.
Catálogo de livros com integração via API, persistência em banco de dados, filtros avançados, estatísticas e exportações para CSV.

![Java](https://img.shields.io/badge/Java-17+-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Maven](https://img.shields.io/badge/Maven-Build-yellow)

---

## 📝 Sobre o Projeto
Este projeto é o resultado do Challenge Backend Java que implementa um catálogo de livros e autores acessado via console, proposto pela **Alura**. A aplicação consome dados da API Gutendex, realiza a persistência em um banco de dados relacional e oferece ferramentas avançadas de análise e exportação.

O objetivo é proporcionar uma experiência prática com o ecossistema Java moderno, focando em persistência de dados, integração com serviços externos e manipulação de arquivos.

Esta aplicação permite:
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
* **Spring Data JPA**
* **PostgreSQL**
* **Jackson** (Manipulação de JSON)
* **Maven** (Gerenciamento de dependências)
* **API Gutendex** (Fonte de dados externa)
* **IntelliJ IDEA**

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

## 🧭 Menu Principal - Navegação (Console)
O menu da aplicação permite as seguintes ações:
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
---

## 📊 Estatísticas (Opção 13)

O sistema gera dados detalhados como:

* **Total de livros cadastrados**
* **Total de autores cadastrados**
* **Total de obras por idiomas**  
* **Ranking dos livros mais baixados**  
* **Ranking dos livros menos baixado**  
* **Total geral de downloads**  
* **Total de autor com mais obras**  
* **Idiomas mais frequentes**
  
As estatísticas foram pensadas para entregar uma visão completa do **acervo API pública Gutendex.**  
Saída completa, detalhada e pronta para gerar relatórios.

---

## 📤 Exportação para CSV (Opção 14)

São gerados:

- `Catálogo completo de Livros`  
- `Catálogo completo de Autores`

Os arquivos CSV podem ser utilizados em:
- UTF-8  
- Seguro para Excel, Power BI, Python/Pandas, R e PostgreSQL.
- Campos limpos (sem quebras de linha ou caracteres inválidos)

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

## ▶️ Como Executar o Projeto
1. Certifique-se de ter o Java 17+ e o Maven instalados, caso contrário realize a instalação.
2. Configure o banco de dados no PostgreSQL com o nome literatura.
3. Clone o repositório: git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
4. Configure o `application.properties` e execute o projeto via IDE ou terminal atraves do comando: mvn spring-boot:run

O menu principal será exibido no console.

---

## 📄 Challenge Original

O PDF oficial do desafio está incluído no repositório, contendo:
* Requisitos
* Etapas
* Regras do CRUD
* Detalhes da API Gutendex

---

## 📌 Possíveis Melhorias Futuras

- Filtros avançados adicionais  
- Exportação em JSON  
- Estatísticas gráficas  
- Interface web com Spring MVC  

---

## 🚀 O que eu aprendi com este projeto

* Consumo de APIs REST com Java
* Parsing de JSON
* Manipulação de coleções (List, Map, Stream API)
* Encapsulamento e boas práticas
* Conversão de dados para CSV
* Padronização de repositórios profissionais
* Organização de projetos Java
* Tratamento de erros e validações

---

## 🏁 Status do Projeto
Concluído com sucesso.
A aplicação funciona de ponta a ponta, com dados limpos, estatísticas funcionais e documentação profissional.

---

## 📄 Licença

Projeto aberto para estudo e evolução.  
Uso livre para fins educacionais.  
Caso queira trocar ideias sobre Java, backend ou melhorias:  
GitHub: https://github.com/Ramos-nunes
