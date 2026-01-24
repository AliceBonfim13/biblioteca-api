package com.alice.libraryapi.service;

import com.alice.libraryapi.model.Autor;
import com.alice.libraryapi.model.GeneroLivro;
import com.alice.libraryapi.model.Livro;
import com.alice.libraryapi.repository.AutorRepository;
import com.alice.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizarSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("1f2d7bd1-bf8a-4e97-9a3e-d9f93299a0a0")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2025, 11, 4));
    }


    @Transactional
    public void executar() {
        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Ana");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1949, 1, 13));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("3625-1427");
        livro.setPreco(BigDecimal.valueOf(500));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Livro da Ana");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Alice")) {
            throw new RuntimeException("Rollaback");
        }
    }
}
