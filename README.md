# Catalogo-Literatura-Alura
"Challenge Alura: Catalogo de Literatura com Java, Spring Boot, Banco de Dados e API".

# Catálogo - Literatura Alura

> Desafio Alura: Catálogo de Literatura com Java, Spring Boot, Banco de Dados e API.

Catálogo de livros com integração via API, persistência em banco de dados, filtros avançados, estatísticas e exportações para CSV.

---

## 📚 Sobre o Projeto

Este projeto implementa um catálogo de livros acessado via console, conhecido como Literatura (inspirado no desafio LiterAlura).  
Ele permite:

- extrair dados de livros via API,
- armazenar livros e autores em um banco PostgreSQL,
- consultar, filtrar e explorar informações,
- gerar estatísticas completas,
- exportar o catálogo para arquivos CSV compatíveis com Excel, BI e bancos externos.

O objetivo é proporcionar uma experiência prática e didática de desenvolvimento Java:

- Consumo de API REST  
- Tratamento de JSON  
- Persistência em banco (JPA/Hibernate)  
- Estruturação de menus interativos  
- Filtros e estatísticas  
- Exportação de dados  

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Jackson (JSON)
- IntelliJ IDEA

---

## 🗂️ Estrutura do Projeto

text src/ └── main/ ├── java/ │ └── literatura/ │ ├── model/ │ │ ├── Livro.java │ │ └── Autor.java │ ├── repository/ │ │ ├── LivroRepository.java │ │ └── AutorRepository.java │ ├── service/ │ │ ├── LivroService.java │ │ └── AutorService.java │ ├── client/ │ │ └── GutendexClient.java │ └── LiteraturaCatalogoApplication.java └── resources/ └── application.properties

---

## ⚙️ Configuração do Banco de Dados

No arquivo `application.properties`:

<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">properties</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copiar</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-ud7rk8k3q" style="color:white;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;background:#011627;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-properties" style="white-space:pre;color:#d6deeb;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span class="token key" style="color:rgb(173, 219, 103);font-style:italic">spring.datasource.url</span><span class="token" style="color:rgb(199, 146, 234)">=</span><span class="token value" style="color:rgb(255, 203, 139)">jdbc:postgresql://localhost:5432/literatura</span><span>
</span><span></span><span class="token key" style="color:rgb(173, 219, 103);font-style:italic">spring.datasource.username</span><span class="token" style="color:rgb(199, 146, 234)">=</span><span class="token value" style="color:rgb(255, 203, 139)">seu_usuario</span><span>
</span><span></span><span class="token key" style="color:rgb(173, 219, 103);font-style:italic">spring.datasource.password</span><span class="token" style="color:rgb(199, 146, 234)">=</span><span class="token value" style="color:rgb(255, 203, 139)">sua_senha</span><span>
</span><span></span><span class="token key" style="color:rgb(173, 219, 103);font-style:italic">spring.jpa.hibernate.ddl-auto</span><span class="token" style="color:rgb(199, 146, 234)">=</span><span class="token value" style="color:rgb(255, 203, 139)">update</span><span>
</span><span></span><span class="token key" style="color:rgb(173, 219, 103);font-style:italic">spring.jpa.show-sql</span><span class="token" style="color:rgb(199, 146, 234)">=</span><span class="token value" style="color:rgb(255, 203, 139)">true</span><span>
</span></code></pre></div>

Recomendamos usar o **pgAdmin** para visualizar o banco.

---

## 🌐 Consumo da API

Os livros são obtidos da API pública Gutendex:

- Documentação: https://gutendex.com/books/

A aplicação realiza:

- requisições HTTP
- análise do JSON
- conversão para objetos Java
- persistência automática no banco

---

## 🧭 Menu Principal (Console)

<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">text</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copiar</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-p4negamap" style="color:white;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;background:#011627;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-text" style="white-space:pre;color:#d6deeb;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span>===========================================
</span>           CATALOGO DE LITERATURA
<!-- -->===========================================
<!-- -->
<!-- -->[ CONSULTAS BASICAS ]
<!-- -->  1 - Buscar livro por titulo
<!-- -->  2 - Listar livros cadastrados
<!-- -->  3 - Listar autores cadastrados
<!-- -->  4 - Autores vivos em determinado ano
<!-- -->
<!-- -->[ CONSULTAS AVANCADAS ]
<!-- -->  5 - Listar livros por idioma
<!-- -->  6 - Buscar autor por nome
<!-- -->  8 - Autores nascidos antes de um ano
<!-- -->  9 - Autores falecidos depois de um ano
<!-- --> 10 - Autores vivos entre dois anos
<!-- --> 11 - Autores sem falecimento (vivos hoje)
<!-- --> 12 - Autores mortos antes de um ano
<!-- -->
<!-- -->[ RANKING &amp; ESTATISTICAS ]
<!-- -->  7 - Top 10 livros mais baixados
<!-- --> 13 - Estatisticas gerais (Livros e Autores)
<!-- -->
<!-- -->[ FERRAMENTAS ]
<!-- --> 14 - Exportar catalogo para CSV
<!-- -->
<!-- -->[ SISTEMA ]
<!-- -->  0 - Sair
</code></pre></div>

---

## 📊 Estatísticas (Opção 13)

O sistema calcula automaticamente:

- Total de livros cadastrados  
- Total de autores cadastrados  
- Total de idiomas no catálogo  
- Maior e menor número de downloads  
- Média de downloads  
- Autor com mais livros  
- Línguas mais comuns no acervo  

A saída é específica e pronta para uso em documentos ou relatórios.

---

## 📤 Exportação para CSV (Opção 14)

São gerados dois arquivos:

- `catalogo_livros.csv`
- `catalogo_autores.csv`

Com:

- títulos limpos  
- sem caracteres problemáticos  
- compatíveis com Excel, Power BI, Python, PostgreSQL etc.  

Cada campo é tratado para evitar quebra de linha e garantir integridade.

---

## 🔍 Principais Funcionalidades

- Buscar livro por título  
- Listar livros cadastrados  
- Listar autores cadastrados  
- Listar livros por idioma  
- Consultar autores vivos em determinado ano  
- Consultas avançadas sobre datas de nascimento e falecimento  
- Top 10 livros mais baixados  
- Estatísticas gerais (livros e autores)  
- Exportação do catálogo para CSV  
- Todas as informações persistidas em PostgreSQL  

---

## ▶️ Como Executar

Clonar o repositório:

<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">bash</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copiar</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-5wm81r5df" style="color:white;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;background:#011627;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-bash" style="white-space:pre;color:#d6deeb;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span class="token" style="color:rgb(130, 170, 255)">git</span><span> clone https://github.com/seuusuario/seu-repositorio.git
</span><span></span><span class="token" style="color:rgb(255, 203, 139)">cd</span><span> seu-repositorio
</span></code></pre></div>

Configurar o PostgreSQL no `application.properties` e depois executar:

<div class="widget code-container remove-before-copy"><div class="code-header non-draggable"><span class="iaf s13 w700 code-language-placeholder">bash</span><div class="code-copy-button"><span class="iaf s13 w500 code-copy-placeholder">Copiar</span><img class="code-copy-icon" src="data:image/svg+xml;utf8,%0A%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%3E%0A%20%20%3Cpath%20d%3D%22M10.8%208.63V11.57C10.8%2014.02%209.82%2015%207.37%2015H4.43C1.98%2015%201%2014.02%201%2011.57V8.63C1%206.18%201.98%205.2%204.43%205.2H7.37C9.82%205.2%2010.8%206.18%2010.8%208.63Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%20%20%3Cpath%20d%3D%22M15%204.42999V7.36999C15%209.81999%2014.02%2010.8%2011.57%2010.8H10.8V8.62999C10.8%206.17999%209.81995%205.19999%207.36995%205.19999H5.19995V4.42999C5.19995%201.97999%206.17995%200.999992%208.62995%200.999992H11.57C14.02%200.999992%2015%201.97999%2015%204.42999Z%22%20stroke%3D%22%23717C92%22%20stroke-width%3D%221.05%22%20stroke-linecap%3D%22round%22%20stroke-linejoin%3D%22round%22%2F%3E%0A%3C%2Fsvg%3E%0A" /></div></div><pre id="code-bzgo8r8ok" style="color:white;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;white-space:pre;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none;padding:8px;margin:8px;overflow:auto;background:#011627;width:calc(100% - 8px);border-radius:8px;box-shadow:0px 8px 18px 0px rgba(120, 120, 143, 0.10), 2px 2px 10px 0px rgba(255, 255, 255, 0.30) inset"><code class="language-bash" style="white-space:pre;color:#d6deeb;font-family:Consolas, Monaco, &quot;Andale Mono&quot;, &quot;Ubuntu Mono&quot;, monospace;text-align:left;word-spacing:normal;word-break:normal;word-wrap:normal;line-height:1.5;font-size:1em;-moz-tab-size:4;-o-tab-size:4;tab-size:4;-webkit-hyphens:none;-moz-hyphens:none;-ms-hyphens:none;hyphens:none"><span>mvn spring-boot:run
</span></code></pre></div>

O menu aparecerá no console.

---

## 📌 Próximas Melhorias (Opcional)

- Ordenação customizável no menu  
- Logs mais detalhados  
- Suporte a múltiplas APIs  
- Exportação em JSON  
- Interface web com Spring MVC  

---

## 📄 Licença

Projeto aberto para estudo e evolução.  
Uso livre para fins educacionais.
