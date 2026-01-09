package com.literatura.literaturaCatalogo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultado(
        Integer count,
        String next,
        String previous,
        List<DadosLivro> results
) {}
