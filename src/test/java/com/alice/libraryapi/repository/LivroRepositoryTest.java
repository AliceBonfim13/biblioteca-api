package com.alice.libraryapi.repository;

import com.alice.libraryapi.model.Autor;
import com.alice.libraryapi.model.GeneroLivro;
import com.alice.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("89547-4512");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("e1d3f2ad-2c64-497e-815d-e4854359dd69")).orElse(null);
        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("89547-4512");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Marta");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1949, 1, 13));

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("89547-4512");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Mais um livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Alice");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1949, 1, 13));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("8c4e5aaf-08c2-434c-abaf-7c346ddef3be");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("842d7926-10ee-4c55-8075-56a3b02c72cf");
        var marta = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(marta);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("e1d3f2ad-2c64-497e-815d-e4854359dd69");
        repository.deleteById(id);
    }

    @Test
    void buscarLivroTest() {
        UUID id = UUID.fromString("1f2d7bd1-bf8a-4e97-9a3e-d9f93299a0a0");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Mia Culpa");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsndTest() {
        Optional<Livro> lista = repository.findByIsbn("3256-7458");
        lista.ifPresent(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        var resultado = repository.listarTotosPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresComQueryJPQL() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros() {
        var resultado = repository.listarNomeDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiro() {
        var resultado = repository.listarGenerosAutoresBraileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryPoitionalParamTest() {
        var resultado = repository.findByGeneroPositionalParamters(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGenero() {
        repository.deleteByGenero(GeneroLivro.MISTERIO);
    }

    @Test
    void updateDataPublicacao() {
        repository.updateDataPublicacao(LocalDate.of(2000, 9, 25));
    }

}



















