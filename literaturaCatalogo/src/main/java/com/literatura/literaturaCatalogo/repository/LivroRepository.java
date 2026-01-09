package com.literatura.literaturaCatalogo.repository;

import com.literatura.literaturaCatalogo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    List<Livro> findByIdiomaIgnoreCase(String idioma);

    // MÉTODO NOVO PARA O TOP 10
    List<Livro> findTop10ByOrderByDownloadsDesc();
}
