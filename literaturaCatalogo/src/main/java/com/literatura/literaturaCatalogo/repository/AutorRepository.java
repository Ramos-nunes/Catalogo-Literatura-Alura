package com.literatura.literaturaCatalogo.repository;

import com.literatura.literaturaCatalogo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    List<Autor> findByNomeContainingIgnoreCase(String nomeAutorBusca);

    // --- CONSULTAS PADRÃO DO PROJETO ---
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(
            Integer anoNascimento,
            Integer anoFalecimento
    );

    // --- CONSULTAS COM JOIN FETCH PARA EVITAR LazyInitializationException ---

    // 1) Buscar TODOS os autores já trazendo os livros
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> buscarTodosComLivros();

    // 2) Autores nascidos antes de X
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros WHERE a.anoNascimento < :ano")
    List<Autor> buscarAutoresNascidosAntes(@Param("ano") Integer ano);

    // 3) Autores falecidos depois de X
    @Query("""
           SELECT DISTINCT a 
           FROM Autor a 
           LEFT JOIN FETCH a.livros 
           WHERE a.anoFalecimento IS NOT NULL AND a.anoFalecimento > :ano
           """)
    List<Autor> buscarAutoresFalecidosDepois(@Param("ano") Integer ano);

    // 4) Autores vivos entre ano1 e ano2
    @Query("""
           SELECT DISTINCT a 
           FROM Autor a 
           LEFT JOIN FETCH a.livros 
           WHERE a.anoNascimento <= :anoFinal
             AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :anoInicial)
           """)
    List<Autor> buscarAutoresVivosEntre(
            @Param("anoInicial") Integer anoInicial,
            @Param("anoFinal") Integer anoFinal);

    // 5) Autores que NÃO têm ano de falecimento (vivos hoje)
    @Query("""
           SELECT DISTINCT a 
           FROM Autor a 
           LEFT JOIN FETCH a.livros 
           WHERE a.anoFalecimento IS NULL
           """)
    List<Autor> buscarAutoresSemFalecimento();

    // 6) Autores mortos antes de X
    @Query("""
           SELECT DISTINCT a
           FROM Autor a
           LEFT JOIN FETCH a.livros
           WHERE a.anoFalecimento IS NOT NULL AND a.anoFalecimento < :ano
           """)
    List<Autor> buscarAutoresMortosAntes(@Param("ano") Integer ano);
}