package com.alice.libraryapi.service;

import com.alice.libraryapi.model.GeneroLivro;
import com.alice.libraryapi.model.Livro;
import com.alice.libraryapi.repository.LivroRepository;
import com.alice.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.alice.libraryapi.repository.specs.LivroSpecs.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
       return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {

        return  livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            String nomeAutor,
            GeneroLivro genero,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina
    ) {
        Specification<Livro> specs = Specification.allOf();

        if (isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

        return livroRepository.findAll(specs, pageable);
    }

    public void atualizar(Livro livro) {
        if(livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo na base.");
        }

        validator.validar(livro);
        livroRepository.save(livro);
    }

}
