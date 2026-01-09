# Catalogo-Literatura-Alura
"Challenge Alura: Catalogo de Literatura com Java, Spring Boot, Banco de Dados e API".

Catálogo de livros com integração via API, persistência em banco de dados, filtros avançados, estatísticas e exportação para CSV.

📚 Sobre o Projeto
Este projeto implementa um catálogo de livros acessado via console, conhecido como Literatura (inspirado no desafio LiterAlura).
Ele permite:

consumir dados de livros via API,
armazenar livros e autores em um banco PostgreSQL,
consultar, filtrar e explorar informações,
gerar estatísticas completas,
exportar o catálogo para arquivos CSV compatíveis com Excel, BI e bancos externos.
O objetivo é proporcionar uma experiência prática e didática de desenvolvimento Java:

Consumo de API REST
Tratamento de JSON
Persistência em banco (JPA/Hibernate)
Estruturação de menus interativos
Filtros e estatísticas
Exportação de dados
🚀 Tecnologias Utilizadas
Java 17+
Spring Boot
Spring Data JPA
PostgreSQL
Jackson (JSON)
Maven
IntelliJ IDEA
API externa de livros — Gutendex
🗂️ Estrutura do Projeto
src/
 └── main/
     ├── java/
     │   └── literatura/
     │        ├── model/
     │        │     ├── Livro.java
     │        │     └── Autor.java
     │        │
     │        ├── repository/
     │        │     ├── LivroRepository.java
     │        │     └── AutorRepository.java
     │        │
     │        ├── service/
     │        │     ├── LivroService.java
     │        │     └── AutorService.java
     │        │
     │        ├── client/
     │        │     └── GutendexClient.java
     │        │
     │        └── LiteraturaCatalogoApplication.java
     │
     └── resources/
           ├── application.properties
           └── static/ (CSV exportados, se desejado)
⚙️ Configuração do Banco de Dados
No arquivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Recomendado usar o pgAdmin para visualizar o banco.

📂 Consumo da API
Os livros são obtidos da API pública Gutendex:

https://gutendex.com/books/?search=<termo>
O projeto realiza:

requisição HTTP,
parsing do JSON,
conversão para objetos Java,
persistência automática no banco.
🧭 Menu Principal (Console)
O sistema oferece um menu limpo, organizado e fácil de navegar:

===========================================
           CATALOGO DE LITERATURA
===========================================

[ CONSULTAS BASICAS ]
  1 - Buscar livro por titulo
  2 - Listar livros cadastrados
  3 - Listar autores cadastrados
  4 - Autores vivos em determinado ano

[ CONSULTAS AVANCADAS ]
  5 - Listar livros por idioma
  6 - Buscar autor por nome
  8 - Autores nascidos antes de um ano
  9 - Autores falecidos depois de um ano
 10 - Autores vivos entre dois anos
 11 - Autores sem falecimento (vivos hoje)
 12 - Autores mortos antes de um ano

[ RANKING & ESTATISTICAS ]
  7 - Top 10 livros mais baixados
 13 - Estatisticas gerais (Livros e Autores)

[ FERRAMENTAS ]
 14 - Exportar catalogo para CSV

[ SISTEMA ]
  0 - Sair
📊 Estatísticas (Opção 13)
O sistema calcula automaticamente:

Total de livros cadastrados
Total de autores cadastrados
Total de idiomas no catálogo
Maior e menor número de downloads
Média de downloads
Autor com mais livros
Línguas mais comuns no acervo
A saída é detalhada e pronta para uso em documentação ou relatórios.

📤 Exportação para CSV (Opção 14)
Gera automaticamente dois arquivos:

catalogo_livros.csv
catalogo_autores.csv
Com:

títulos limpos
sem caracteres problemáticos
estrutura compatível com Excel, Power BI, Python, PostgreSQL, etc.
Cada campo é tratado para evitar quebra de linha e garantir integridade.

🔍 Principais Funcionalidades
Buscar livro por título
Listar livros cadastrados
Listar autores cadastrados
Listar livros por idioma
Consultas avançadas sobre datas de nascimento e falecimento
Ver os livros mais baixados (Top 10)
Estatísticas gerais completas
Exportação do catálogo para CSV
Todas as informações persistidas em PostgreSQL
🧪 Como Executar
Clone o repositório:
git clone https://github.com/seuusuario/literatura.git
Entre na pasta:
cd literatura
Configure o PostgreSQL no application.properties.

Execute:

mvn spring-boot:run
O menu aparecerá no console.

📌 Próximas Melhorias (Opcional)
Ordenação customizável no menu
Logs mais detalhados
Suporte a múltiplas APIs
Exportação em JSON
Interface web com Spring MVC
📄 Licença
Este projeto é aberto para estudo e evolução.
Uso livre para finalidades educacionais.

