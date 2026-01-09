package com.literatura.literaturaCatalogo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        Integer id,
        String title,
        List<DadosAutor> authors,
        List<String> languages,
        @JsonAlias("download_count") Integer downloads
) {}