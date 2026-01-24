package com.alice.libraryapi.controller.mappers;

import com.alice.libraryapi.controller.dto.CadastroLivroDTO;
import com.alice.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.alice.libraryapi.model.Livro;
import com.alice.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class )
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    @Mapping(source = "autor", target = "autorDTO")
    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}