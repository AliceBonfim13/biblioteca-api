package com.alice.libraryapi.repository;

import com.alice.libraryapi.model.Autor;
import com.alice.libraryapi.model.GeneroLivro;
import com.alice.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

    boolean existsByAutor(Autor autor);

    // Query Method
    // select * from livro where id_autor = id;
    List<Livro> findByAutor(Autor autor);

    // select * from livro where titulo = titulo;
    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    @Query(" select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTotosPorTituloAndPreco();

    @Query("SELECT a FROM Autor a JOIN FETCH a.livros ")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomeDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            """)
    List<String> listarGenerosAutoresBraileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero")GeneroLivro generoLivro, @Param("paramOrdenacao") String nomePropriedade);

       // positional parameters
    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGeneroPositionalParamters(GeneroLivro generoLivro, String nomePropriedade);


    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1 ")
    void updateDataPublicacao(LocalDate novaData);

}
