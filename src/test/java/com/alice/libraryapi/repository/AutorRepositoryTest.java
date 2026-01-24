package com.alice.libraryapi.repository;


import com.alice.libraryapi.model.Autor;
import com.alice.libraryapi.model.GeneroLivro;
import com.alice.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1949, 1, 13));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }


    @Test
    public void atualizarTest() {
        var id = UUID.fromString("0149b693-5401-455a-b0ef-f2eec8c1083b");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do AUTOR: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1940, 12, 4));
            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listaeTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("0149b693-5401-455a-b0ef-f2eec8c1083b");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("032c6a62-9130-4d9d-8e73-edcad21e4192");
        var autor = repository.findById(id).get();
        repository.delete(autor);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1929, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("3256-7458");
        livro.setPreco(BigDecimal.valueOf(270));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Mia Culpa");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("777-7458");
        livro2.setPreco(BigDecimal.valueOf(170));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("Tua Culpa");
        livro2.setDataPublicacao(LocalDate.of(1950, 9, 13));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }

    @Test
    @Transactional
    void listarLivrosAutor() {
        var id = UUID.fromString("ea871140-792e-47f3-8439-4bf63f8cf502");
        var autor = repository.findById(id).get();

        // buscar os livros do autor
        List<Livro> livroLista = livroRepository.findByAutor(autor);
        autor.setLivros(livroLista);

        autor.getLivros().forEach(System.out::println);
    }

}


