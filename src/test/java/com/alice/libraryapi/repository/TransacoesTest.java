package com.alice.libraryapi.repository;

import com.alice.libraryapi.service.TransacaoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar as alteracoes
     * Rollback -> desfazer as alteracoes
     */

    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }

}
