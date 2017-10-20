/*
 * Copyright (c) 1999-2009 Touch Tecnologia e Informatica Ltda.
 * Gomes de Carvalho, 1666, 3o. Andar, Vila Olimpia, Sao Paulo, SP, Brasil.
 * Todos os direitos reservados.
 *
 * Este software e confidencial e de propriedade da Touch Tecnologia e
 * Informatica Ltda. (Informacao Confidencial). As informacoes contidas neste
 * arquivo nao podem ser publicadas, e seu uso esta limitado de acordo com os
 * termos do contrato de licenca.
 */

package br.com.touchtec.games.core.service.impl;


import java.util.Arrays;
import java.util.GregorianCalendar;

import org.junit.Test;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;

public class JogoServiceImplTest {

    private JogoService service = new JogoServiceImpl();


    @Test
    public void criarTest() {
        // EXERCICIO
    }

    @Test
    public void editarTest() {
        // EXERCICIO
    }

    @Test
    public void removerTest() {
        // EXERCICIO
    }


    @Test
    public void buscarTodosTest() {
        // EXERCICIO
    }


    @Test
    public void buscarPorNomeTest() {
        // EXERCICIO
    }


    /**********************************************************************************************************************/
    // METODOS AUXILIARES

    /**********************************************************************************************************************/

    private Jogo criarJogo(String nome) {
        return this.criarJogo(nome, null);
    }

    private Jogo criarJogo(String nome, Genero genero, Plataforma... plataformas) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setDescricao("Melhor jogo de todos os tempos.");
        jogo.setDataLancamento(new GregorianCalendar(2002, 4, 29).getTime());
        jogo.setDesconto(10);
        jogo.setGenero(genero);
        jogo.setPreco(15.0f);
        jogo.setPlataformas(Arrays.asList(plataformas));

        this.service.criar(jogo);
        return jogo;
    }
}
