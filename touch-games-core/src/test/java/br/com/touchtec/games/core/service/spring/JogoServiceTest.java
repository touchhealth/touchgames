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


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
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

    @Autowired
    private PlataformaService plataformaService;

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

    /***/
    public void listarPorPlataformaTest() {
        Jogo skyrin = this.criarJogo("The Elder Scrolls V: Skyrim", "Xbox 360", "PC", "PS3");
        Jogo stc = this.criarJogo("Shadow of the Colossus", "PS3", "PS2");

        Plataforma ps3 = null;
        Plataforma pc = null;
        for (Plataforma p : this.plataformaService.listarTodos()) {
            if ("PS3".equals(p.getNome())) {
                ps3 = p;
            }
            if ("PC".equals(p.getNome())) {
                pc = p;
            }
        }

        List<Jogo> jogos = this.service.listar(ps3);
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(skyrin));
        Assert.assertTrue(jogos.contains(stc));

        jogos = this.service.listar(pc);
        Assert.assertEquals(1, jogos.size());
        Assert.assertTrue(jogos.contains(skyrin));
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
        return this.criarJogo(nome, Genero.RPG);
    }

    private Jogo criarJogo(String nome, String... plataformas) {
        return this.criarJogo(nome, Genero.RPG, plataformas);
    }

    private Jogo criarJogo(String nome, Genero genero, String... plataformas) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setDescricao("Melhor jogo de todos os tempos.");
        jogo.setDataLancamento(new GregorianCalendar(2002, 4, 29).getTime());
        jogo.setDesconto(10);
        jogo.setGenero(genero);
        jogo.setPreco(15.0f);

        if (ArrayUtils.isNotEmpty(plataformas)) {
            List<Plataforma> listaPlataformas = new ArrayList<Plataforma>();
            for (String plataforma : plataformas) {
                listaPlataformas.add(this.criarPlataforma(plataforma));
            }
            jogo.setPlataformas(listaPlataformas);
        }

        this.service.criar(jogo);
        return jogo;
    }

    private Plataforma criarPlataforma(String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        this.plataformaService.criar(plataforma);
        return plataforma;
    }
}
