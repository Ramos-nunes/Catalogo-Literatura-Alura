# Catálogo de Literatura 📚

> Challenge Alura: Catálogo de Literatura com Java, Spring Boot, Banco de Dados e API.
Catálogo de livros com integração via API, persistência em banco de dados, filtros avançados, estatísticas e exportações para CSV.

![Java](https://img.shields.io/badge/Java-17+-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Maven](https://img.shields.io/badge/Maven-Build-yellow)

---

## 📝 Sobre o Projeto
Este projeto implementa um catálogo de livros acessado via console, inspirado no desafio **LiterAlura**. A aplicação consome dados da API Gutendex, realiza a persistência em um banco de dados relacional e oferece ferramentas avançadas de análise e exportação.

O objetivo é proporcionar uma experiência prática com o ecossistema Java moderno, focando em persistência de dados, integração com serviços externos e manipulação de arquivos.

A aplicação permite e proporciona experiência prática em:
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

## 🧭 Menu Principal (Console)
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

O sistema calcula:

- total de livros cadastrados  
- total de autores cadastrados  
- idiomas presentes  
- livro mais baixado  
- livro menos baixado  
- média geral de downloads  
- autor com mais livros  
- idiomas mais frequentes  

Saída completa, detalhada e pronta para relatórios.

---

## 📤 Exportação para CSV (Opção 14)

São gerados:

- `catalogo_livros.csv`  
- `catalogo_autores.csv`

Formato:
- UTF-8  
- seguro para Excel, Power BI, Python e PostgreSQL
- campos limpos (sem quebras de linha ou caracteres inválidos)

---

## ▶️ Como Executar
1. Certifique-se de ter o Java 17 e o Maven instalados.
2. Configure o banco de dados no PostgreSQL com o nome literatura.
3. Clone o repositório: git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
4. Configure o `application.properties` e execute o projeto via IDE ou terminal atraves do comando: mvn spring-boot:run

O menu será exibido no console.

---

## 📌 Possíveis Melhorias Futuras

- filtros avançados adicionais  
- exportação em JSON  
- estatísticas gráficas  
- interface web com Spring MVC  

---

## 📄 Licença

Projeto aberto para estudo e evolução.  
Uso livre para fins educacionais.
