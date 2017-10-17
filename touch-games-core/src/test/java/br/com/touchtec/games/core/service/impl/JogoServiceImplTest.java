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
import java.util.List;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import junit.framework.Assert;

public class JogoServiceImplTest {

    private JogoService service = new JogoServiceImpl();

    @After
    public void limpaBanco() {
        /**
         * Funciona porque no persistence.xml setamos "hibernate.hbm2ddl.auto=create-drop"
         */
        Persistence.createEntityManagerFactory("touch-games");
    }

    @Test
    public void criarTest() {
        Jogo jogo = this.criarJogo("Final Fantasy VIII");
        Jogo jogoDB = this.service.recuperar(jogo.getId());
        Assert.assertEquals(jogo, jogoDB);
    }

    @Test
    public void editarTest() {
        Jogo jogo = this.criarJogo("Final Fantasy X");
        jogo.setDesconto(66);
        this.service.editar(jogo);

        Jogo jogoDB = this.service.recuperar(jogo.getId());

        Assert.assertEquals(66, jogoDB.getDesconto());
    }

    @Test
    public void removerTest() {
        Jogo jogo = this.criarJogo("Final Fantasy XII");
        this.service.remover(jogo);
        Jogo jogoDB = this.service.recuperar(jogo.getId());
        Assert.assertNull(jogoDB);
    }

    @Test
    public void listarPorGeneroTest() {
        Jogo ct = this.criarJogo("Chrono Trigger", Genero.RPG);
        Jogo cc = this.criarJogo("Chrono Cross", Genero.RPG);
        Jogo fifa = this.criarJogo("Fifa 14", Genero.ESPORTE);

        List<Jogo> jogos = this.service.listar(Genero.RPG);
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(ct));
        Assert.assertTrue(jogos.contains(cc));

        jogos = this.service.listar(Genero.ESPORTE);
        Assert.assertEquals(1, jogos.size());
        Assert.assertTrue(jogos.contains(fifa));

        jogos = this.service.listar(Genero.ACAO);
        Assert.assertEquals(0, jogos.size());
    }

    @Test
    public void listarTodosTest() {
        Jogo ct = this.criarJogo("Chrono Trigger");
        Jogo cc = this.criarJogo("Chrono Cross");

        List<Jogo> jogos = this.service.listarTodos();

        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(ct));
        Assert.assertTrue(jogos.contains(cc));
    }

    @Test
    public void buscarTest() {
        Jogo ct = this.criarJogo("Chrono Trigger");
        Jogo cc = this.criarJogo("Chrono Cross");
        this.criarJogo("Final Fantasy VII");

        List<Jogo> jogos = this.service.buscar("HrOnO");
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(ct));
        Assert.assertTrue(jogos.contains(cc));

        jogos = this.service.buscar("Bazinga");
        Assert.assertEquals(0, jogos.size());
    }


    private Jogo criarJogo(String nome) {
        return this.criarJogo(nome, Genero.RPG);
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
