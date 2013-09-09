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

package br.com.touchtec.games.core.service.spring;


import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.spring.SpringBeanUtil;
import br.com.touchtec.spring.test.SpringTestUtil;
import br.com.touchtec.spring.test.TouchSpringRunner;


/**
 * @author emesquita
 */
@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = br.com.touchtec.spring.test.SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class JogoServiceTest {

    @Autowired
    private JogoService service;

    /***/
    @Test
    public void criarTest() {
        Jogo jogo = this.criarJogo("Final Fantasy VIII");
        Jogo jogoDB = this.service.recuperar(jogo.getId());
        Assert.assertEquals(jogo, jogoDB);
    }

    /***/
    @Test
    public void editarTest() {
        Jogo jogo = this.criarJogo("Final Fantasy X");
        jogo.setDesconto(66);
        this.service.editar(jogo);

        Jogo jogoDB = this.service.recuperar(jogo.getId());

        Assert.assertEquals(66, jogoDB.getDesconto());
    }

    /***/
    @Test
    public void removerTest() {
        Jogo jogo = this.criarJogo("Final Fantasy XII");
        this.service.remover(jogo);
        Jogo jogoDB = this.service.recuperar(jogo.getId());
        Assert.assertNull(jogoDB);
    }

    /***/
    @Test
    public void listarTodosTest() {
        Jogo ct = this.criarJogo("Chrono Trigger");
        Jogo cc = this.criarJogo("Chrono Cross");

        List<Jogo> jogos = this.service.listarTodos();

        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(ct));
        Assert.assertTrue(jogos.contains(cc));
    }

    /***/
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

    /***/
    @After
    public void after() {
        SpringTestUtil.restartContext(SpringBeanUtil.getContext());
    }

    private Jogo criarJogo(String nome) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setDescricao("Melhor jogo de todos os tempos.");
        jogo.setDataLancamento(new GregorianCalendar(2002, 4, 29).getTime());
        jogo.setDesconto(10);
        jogo.setGenero(Genero.RPG);
        jogo.setPreco(15.0f);
        this.service.criar(jogo);
        return jogo;
    }
}