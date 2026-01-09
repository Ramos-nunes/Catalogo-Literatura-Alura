package com.literatura.literaturaCatalogo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.text.DecimalFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.literatura.literaturaCatalogo.api.GutendexClient;
import com.literatura.literaturaCatalogo.ConverteDados;
import com.literatura.literaturaCatalogo.model.Autor;
import com.literatura.literaturaCatalogo.model.Livro;
import com.literatura.literaturaCatalogo.repository.AutorRepository;
import com.literatura.literaturaCatalogo.repository.LivroRepository;
import com.literatura.literaturaCatalogo.dto.DadosResultado;
import com.literatura.literaturaCatalogo.dto.DadosLivro;
import com.literatura.literaturaCatalogo.dto.DadosAutor;


@SpringBootApplication
public class LiteraturaCatalogoApplication implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public LiteraturaCatalogoApplication(AutorRepository autorRepository,
                                         LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaCatalogoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        var opcao = -1;

        while (opcao != 0) {

            // ===== MENU PRINCIPAL DA LITERATURA =====
            //
            // Estrutura geral do menu da aplicação.
            // Aqui o usuário escolhe as opções de consulta, estatísticas e ferramentas.
            // As opções estão organizadas por seções para facilitar a navegação
            // e manter o código mais legível e profissional.
            //
            System.out.println("\n===========================================");
            System.out.println("            CATÁLOGO DE LITERATURA          ");
            System.out.println("===========================================\n");

            // ===== CONSULTAS BÁSICAS =====
            // Funcionalidades centrais do projeto original da literatura.
            // Permitem buscar livros e autores e visualizar o catálogo salvo no banco.
            //
            System.out.println("[ CONSULTAS BASICAS ]");
            System.out.println("  1 - Buscar livro por titulo");
            System.out.println("  2 - Listar livros cadastrados");
            System.out.println("  3 - Listar autores cadastrados");
            System.out.println("  4 - Autores vivos em determinado ano\n");

            // ===== CONSULTAS AVANÇADAS =====
            // Novas funcionalidades adicionadas após o tema 5.
            // Estas consultas enriquecem a literatura, ampliam as possibilidades de pesquisa
            // e tornam a experiência do usuário mais completa e interessante.
            //
            System.out.println("[ CONSULTAS AVANCADAS ]");
            System.out.println("  5 - Listar livros por idioma");
            System.out.println("  6 - Buscar autor por nome");
            System.out.println("  8 - Autores nascidos antes de um ano");
            System.out.println("  9 - Autores falecidos depois de um ano");
            System.out.println(" 10 - Autores vivos entre dois anos");
            System.out.println(" 11 - Autores sem falecimento (vivos hoje)");
            System.out.println(" 12 - Autores mortos antes de um ano\n");

            // ===== RANKING E ESTATÍSTICAS =====
            // Funcionalidades de análise do catálogo.
            // Permitem visualizar os livros mais baixados e um resumo estatístico
            // completo do catálogo de livros e autores.
            //
            System.out.println("[ RANKING & ESTATISTICAS ]");
            System.out.println("  7 - Top 10 livros mais baixados");
            System.out.println(" 13 - Estatisticas gerais (Livros e Autores)\n");

            // ===== FERRAMENTAS =====
            // Recursos auxiliares e de integração com outras ferramentas.
            // A exportação para CSV permite usar o catálogo em planilhas,
            // bancos de dados, BI e outras aplicações externas.
            //
            System.out.println("[ FERRAMENTAS ]");
            System.out.println(" 14 - Exportar catalogo para CSV\n");

            // ===== SISTEMA =====
            // Opções relacionadas ao controle da aplicação.
            //
            System.out.println("[ SISTEMA ]");
            System.out.println("  0 - Sair\n");

            System.out.println("-------------------------------------------");
            System.out.print("Escolha uma opcao: ");

            // leitura da opção do usuário vem logo em seguida...

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite apenas números.");
                continue;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Digite o nome do livro: ");
                    var tituloBuscado = scanner.nextLine();

                    GutendexClient client = new GutendexClient();
                    ConverteDados conversor = new ConverteDados();

                    String json = client.buscarLivro(tituloBuscado);
                    DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

                    if (resultado.results().isEmpty()) {
                        System.out.println("\nNenhum livro encontrado com esse título na API.");
                        break;
                    }

                    DadosLivro dadosLivro = resultado.results().get(0);

                    System.out.println("\n=== LIVRO ENCONTRADO NA API ===");
                    System.out.println("Título: " + dadosLivro.title());
                    System.out.println("Idiomas: " + dadosLivro.languages());
                    System.out.println("Downloads: " + dadosLivro.downloads());

                    if (dadosLivro.authors().isEmpty()) {
                        System.out.println("Este livro não possui autores cadastrados na API.");
                        break;
                    }

                    DadosAutor dadosAutor = dadosLivro.authors().get(0);

                    System.out.println("Autor: " + dadosAutor.nome());
                    System.out.println("Nascimento: " + dadosAutor.anoNascimento());
                    System.out.println("Falecimento: " + dadosAutor.anoFalecimento());
                    System.out.println("----------------------------");

                    var livroExistente = livroRepository.findByTitulo(dadosLivro.title());
                    if (livroExistente.isPresent()) {
                        System.out.println("Este livro já está cadastrado no banco de dados.");
                        break;
                    }

                    var autorExistente = autorRepository.findByNome(dadosAutor.nome());
                    Autor autor;

                    if (autorExistente.isPresent()) {
                        autor = autorExistente.get();
                    } else {
                        autor = new Autor(
                                dadosAutor.nome(),
                                dadosAutor.anoNascimento(),
                                dadosAutor.anoFalecimento()
                        );
                        autorRepository.save(autor);
                    }

                    String idioma = dadosLivro.languages().isEmpty() ? null : dadosLivro.languages().get(0);

                    Livro livro = new Livro(
                            dadosLivro.title(),
                            idioma,
                            dadosLivro.downloads(),
                            autor
                    );

                    livroRepository.save(livro);
                    System.out.println("Livro salvo com sucesso no banco de dados!");
                    break;

                case 2:
                    var livros = livroRepository.findAll();

                    if (livros.isEmpty()) {
                        System.out.println("\nNão há livros cadastrados no banco de dados.");
                        break;
                    }

                    System.out.println("\n=== LIVROS CADASTRADOS ===");
                    livros.forEach(l -> {
                        System.out.println("Título: " + l.getTitulo());
                        System.out.println("Idioma: " + l.getIdioma());
                        System.out.println("Downloads: " + l.getDownloads());
                        if (l.getAutor() != null) {
                            System.out.println("Autor: " + l.getAutor().getNome());
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 3:
                    var autores = autorRepository.findAll();

                    if (autores.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados no banco de dados.");
                        break;
                    }

                    System.out.println("\n=== AUTORES CADASTRADOS ===");
                    autores.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Nascimento: " + a.getAnoNascimento());
                        System.out.println("Falecimento: " + a.getAnoFalecimento());
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            System.out.println("Livros:");
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 4:
                    System.out.print("Digite o ano para buscar autores vivos: ");
                    var anoTexto = scanner.nextLine();

                    Integer ano;
                    try {
                        ano = Integer.parseInt(anoTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Ano inválido. Digite apenas números.");
                        break;
                    }

                    var autoresVivos =
                            autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);

                    if (autoresVivos.isEmpty()) {
                        System.out.println("\nNenhum autor encontrado vivo no ano de " + ano + ".");
                        break;
                    }

                    System.out.println("\n=== AUTORES VIVOS EM " + ano + " ===");
                    autoresVivos.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Nascimento: " + a.getAnoNascimento());
                        System.out.println("Falecimento: " + a.getAnoFalecimento());
                        System.out.println("----------------------------");
                    });
                    break;

                case 5:
                    System.out.print("Digite o idioma (ex: pt, en, es, fr): ");
                    var idiomaBusca = scanner.nextLine().trim();

                    var livrosPorIdioma = livroRepository.findByIdiomaIgnoreCase(idiomaBusca);

                    if (livrosPorIdioma.isEmpty()) {
                        System.out.println("\nNão existem livros cadastrados no idioma \"" + idiomaBusca + "\".");
                        break;
                    }

                    System.out.println("\n=== LIVROS NO IDIOMA \"" + idiomaBusca.toUpperCase() + "\" ===");
                    livrosPorIdioma.forEach(l -> {
                        System.out.println("Título: " + l.getTitulo());
                        System.out.println("Downloads: " + l.getDownloads());
                        if (l.getAutor() != null) {
                            System.out.println("Autor: " + l.getAutor().getNome());
                        }
                        System.out.println("----------------------------");
                    });

                    // ===== Trecho que atende o Trello: quantidade de livros por idioma =====
                    System.out.println("Quantidade de livros no idioma \""
                            + idiomaBusca.toLowerCase() + "\": " + livrosPorIdioma.size());
                    // ======================================================================

                    break;

                case 6:
                    System.out.print("Digite o nome do autor para buscar: ");
                    var nomeAutorBusca = scanner.nextLine();

                    var autoresEncontrados =
                            autorRepository.findByNomeContainingIgnoreCase(nomeAutorBusca);

                    if (autoresEncontrados.isEmpty()) {
                        System.out.println("\nNenhum autor encontrado com o nome informado.");
                        break;
                    }

                    System.out.println("\n=== AUTORES ENCONTRADOS ===");
                    autoresEncontrados.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Nascimento: " + a.getAnoNascimento());
                        System.out.println("Falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro encontrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 7:
                    System.out.println("\n=== TOP 10 LIVROS MAIS BAIXADOS ===");

                    var topLivros = livroRepository.findTop10ByOrderByDownloadsDesc();

                    if (topLivros.isEmpty()) {
                        System.out.println("Ainda não há livros cadastrados no banco de dados.");
                        break;
                    }

                    final int[] posicao = {1};
                    topLivros.forEach(l -> {
                        System.out.println(posicao[0] + "º - " + l.getTitulo());
                        System.out.println("     Downloads: " + l.getDownloads());
                        if (l.getAutor() != null) {
                            System.out.println("     Autor: " + l.getAutor().getNome());
                        }
                        System.out.println("     Idioma: " + l.getIdioma());
                        System.out.println("----------------------------");
                        posicao[0]++;
                    });

                case 8:
                    System.out.print("Digite o ano para buscar autores nascidos ANTES dele: ");
                    var anoAntes = Integer.parseInt(scanner.nextLine());

                    var autoresNascidosAntes = autorRepository.buscarAutoresNascidosAntes(anoAntes);

                    if (autoresNascidosAntes.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados nascidos antes de " + anoAntes + ".");
                        break;
                    }

                    System.out.println("\n=== AUTORES NASCIDOS ANTES DE " + anoAntes + " ===");
                    autoresNascidosAntes.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                        System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro cadastrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 9:
                    System.out.print("Digite o ano para buscar autores FALECIDOS DEPOIS dele: ");
                    var anoDepois = Integer.parseInt(scanner.nextLine());

                    var autoresFalecidosDepois = autorRepository.buscarAutoresFalecidosDepois(anoDepois);

                    if (autoresFalecidosDepois.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados falecidos depois de " + anoDepois + ".");
                        break;
                    }

                    System.out.println("\n=== AUTORES FALECIDOS DEPOIS DE " + anoDepois + " ===");
                    autoresFalecidosDepois.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                        System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro cadastrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 10:
                    System.out.print("Digite o ANO INICIAL para buscar autores vivos no intervalo: ");
                    var anoInicial = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o ANO FINAL para buscar autores vivos no intervalo: ");
                    var anoFinal = Integer.parseInt(scanner.nextLine());

                    var autoresVivosEntre = autorRepository.buscarAutoresVivosEntre(anoInicial, anoFinal);

                    if (autoresVivosEntre.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados vivos entre " + anoInicial + " e " + anoFinal + ".");
                        break;
                    }

                    System.out.println("\n=== AUTORES VIVOS ENTRE " + anoInicial + " E " + anoFinal + " ===");
                    autoresVivosEntre.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                        System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro cadastrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 11:
                    System.out.println("Buscando autores que NÃO possuem ano de falecimento (potencialmente vivos)...");

                    var autoresSemFalecimento = autorRepository.buscarAutoresSemFalecimento();

                    if (autoresSemFalecimento.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados sem ano de falecimento.");
                        break;
                    }

                    System.out.println("\n=== AUTORES SEM ANO DE FALECIMENTO (VIVOS HOJE) ===");
                    autoresSemFalecimento.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                        System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro cadastrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 12:
                    System.out.print("Digite o ano para buscar autores MORTOS ANTES dele: ");
                    var anoMorteAntes = Integer.parseInt(scanner.nextLine());

                    var autoresMortosAntes = autorRepository.buscarAutoresMortosAntes(anoMorteAntes);

                    if (autoresMortosAntes.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados mortos antes de " + anoMorteAntes + ".");
                        break;
                    }

                    System.out.println("\n=== AUTORES MORTOS ANTES DE " + anoMorteAntes + " ===");
                    autoresMortosAntes.forEach(a -> {
                        System.out.println("Autor: " + a.getNome());
                        System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                        System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                        System.out.println("Livros:");
                        if (a.getLivros() != null && !a.getLivros().isEmpty()) {
                            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                        } else {
                            System.out.println(" - Nenhum livro cadastrado.");
                        }
                        System.out.println("----------------------------");
                    });
                    break;

                case 13:

                    var livrosEstatisticas = livroRepository.findAll();

                    if (livrosEstatisticas.isEmpty()) {
                        System.out.println("\nNão há livros cadastrados no catálogo.");
                        break;
                    }

                    var statsDownloads = livrosEstatisticas.stream()
                            .mapToInt(Livro::getDownloads)
                            .summaryStatistics();

                    var livroMaisBaixado = livrosEstatisticas.stream()
                            .max(Comparator.comparingInt(Livro::getDownloads))
                            .orElse(null);

                    var livroMenosBaixado = livrosEstatisticas.stream()
                            .min(Comparator.comparingInt(Livro::getDownloads))
                            .orElse(null);

                    var livrosPorIdiomaEstatisticas = livrosEstatisticas.stream()
                            .collect(Collectors.groupingBy(Livro::getIdioma, Collectors.counting()));

                    var autoresEstatisticas = autorRepository.buscarTodosComLivros();

                    long autoresVivosEstatisticas = autoresEstatisticas.stream()
                            .filter(a -> a.getAnoFalecimento() == null)
                            .count();

                    long autoresFalecidosEstatisticas = autoresEstatisticas.size() - autoresVivosEstatisticas;

                    var autorMaisAntigo = autoresEstatisticas.stream()
                            .filter(a -> a.getAnoNascimento() != null)
                            .min(Comparator.comparing(Autor::getAnoNascimento))
                            .orElse(null);

                    var autorMaisRecente = autoresEstatisticas.stream()
                            .filter(a -> a.getAnoNascimento() != null)
                            .max(Comparator.comparing(Autor::getAnoNascimento))
                            .orElse(null);

                    var statsLivrosPorAutor = autoresEstatisticas.stream()
                            .mapToInt(a -> a.getLivros() != null ? a.getLivros().size() : 0)
                            .summaryStatistics();

                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

                    System.out.println("\n=======================================");
                    System.out.println("        ESTATÍSTICAS DO CATÁLOGO");
                    System.out.println("=======================================\n");

                    System.out.println("--- LIVROS ---");
                    System.out.println("Total de livros: " + statsDownloads.getCount());
                    System.out.println("Downloads totais: " + statsDownloads.getSum());
                    System.out.println("Média de downloads: " + decimalFormat.format(statsDownloads.getAverage()));
                    System.out.println("Mínimo de downloads: " + statsDownloads.getMin());
                    System.out.println("Máximo de downloads: " + statsDownloads.getMax());

                    if (livroMaisBaixado != null) {
                        System.out.println("Livro mais baixado: " + livroMaisBaixado.getTitulo()
                                + " (" + livroMaisBaixado.getDownloads() + ")");
                    }

                    if (livroMenosBaixado != null) {
                        System.out.println("Livro menos baixado: " + livroMenosBaixado.getTitulo()
                                + " (" + livroMenosBaixado.getDownloads() + ")");
                    }

                    System.out.println("\nLivros por idioma:");
                    livrosPorIdiomaEstatisticas.forEach((idiomaEstatistica, qtde) ->
                            System.out.println(" - " + idiomaEstatistica + ": " + qtde + " livro(s)")
                    );

                    System.out.println("\n--- AUTORES ---");
                    System.out.println("Total de autores: " + autoresEstatisticas.size());
                    System.out.println("Autores vivos: " + autoresVivosEstatisticas);
                    System.out.println("Autores falecidos: " + autoresFalecidosEstatisticas);
                    System.out.println("Média de livros por autor: " + decimalFormat.format(statsLivrosPorAutor.getAverage()));

                    if (autorMaisAntigo != null) {
                        System.out.println("Autor mais antigo: " + autorMaisAntigo.getNome()
                                + " (nascido em " + autorMaisAntigo.getAnoNascimento() + ")");
                    }

                    if (autorMaisRecente != null) {
                        System.out.println("Autor mais recente: " + autorMaisRecente.getNome()
                                + " (nascido em " + autorMaisRecente.getAnoNascimento() + ")");
                    }

                    System.out.println("\n---------------------------------------\n");
                    break;

                case 14:
                    // Exporta o catálogo de livros e autores para arquivos CSV
                    var livrosCsv = livroRepository.findAll();
                    var autoresCsv = autorRepository.buscarTodosComLivros();

                    if (livrosCsv.isEmpty()) {
                        System.out.println("\nNão há livros cadastrados no catálogo para exportar.");
                        break;
                    }

                    if (autoresCsv.isEmpty()) {
                        System.out.println("\nNão há autores cadastrados no catálogo para exportar.");
                        break;
                    }

                    // Define diretório base (pasta do projeto)
                    Path diretorioBase = Paths.get("").toAbsolutePath();

                    // Define caminhos dos arquivos CSV
                    Path arquivoLivros = diretorioBase.resolve("catalogo_livros.csv");
                    Path arquivoAutores = diretorioBase.resolve("catalogo_autores.csv");

                    try {
                        // Garante que o diretório base existe (normalmente já existe)
                        if (!Files.exists(diretorioBase)) {
                            Files.createDirectories(diretorioBase);
                        }

                        // Exporta livros para CSV
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLivros.toFile(), false))) {
                            writer.write("id;titulo;idioma;downloads;autor_id;autor_nome");
                            writer.newLine();

                            for (Livro livroCsvItem : livrosCsv) {
                                Autor autorCsvItem = livroCsvItem.getAutor();

                                Long autorId = autorCsvItem != null ? autorCsvItem.getId() : null;
                                String autorNome = autorCsvItem != null ? autorCsvItem.getNome() : "";

                                String linha = String.format(
                                        "%d;%s;%s;%d;%s;%s",
                                        livroCsvItem.getId(),
                                        limparCampoCsv(livroCsvItem.getTitulo()),
                                        livroCsvItem.getIdioma(),
                                        livroCsvItem.getDownloads(),
                                        autorId != null ? autorId.toString() : "",
                                        limparCampoCsv(autorNome)
                                );

                                writer.write(linha);
                                writer.newLine();
                            }

                        }

                        // Exporta autores para CSV
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoAutores.toFile(), false))) {
                            writer.write("id;nome;ano_nascimento;ano_falecimento;total_livros");
                            writer.newLine();

                            for (Autor autorCsvItem : autoresCsv) {
                                int totalLivros = autorCsvItem.getLivros() != null ? autorCsvItem.getLivros().size() : 0;

                                String linha = String.format(
                                        "%d;%s;%s;%s;%d",
                                        autorCsvItem.getId(),
                                        limparCampoCsv(autorCsvItem.getNome()),
                                        autorCsvItem.getAnoNascimento() != null ? autorCsvItem.getAnoNascimento().toString() : "",
                                        autorCsvItem.getAnoFalecimento() != null ? autorCsvItem.getAnoFalecimento().toString() : "",
                                        totalLivros
                                );

                                writer.write(linha);
                                writer.newLine();
                            }

                        }

                        System.out.println("\n=======================================");
                        System.out.println("   EXPORTAÇÃO PARA CSV CONCLUÍDA");
                        System.out.println("=======================================\n");
                        System.out.println("Arquivos gerados na pasta do projeto:");
                        System.out.println(" - " + arquivoLivros.toAbsolutePath());
                        System.out.println(" - " + arquivoAutores.toAbsolutePath());
                        System.out.println("\n---------------------------------------\n");

                    } catch (IOException e) {
                        System.out.println("\nOcorreu um erro ao exportar os arquivos CSV:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");


            }
        }

    }
                // Remove quebras de linha e ponto e vírgula dos campos de texto para não quebrar o CSV
                private String limparCampoCsv(String valor) {
                  if (valor == null) {
                      return "";
                  }
                  return valor
                            .replace(";", ",")
                            .replace("\n", " ")
                            .replace("\r", " ")
                            .trim();
    }

}