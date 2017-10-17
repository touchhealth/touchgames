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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.FabricanteService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.spring.test.SingletonContextLoader;
import br.com.touchtec.spring.test.TouchSpringRunner;
import junit.framework.Assert;


@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class PlataformaServiceImplTest {

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private FabricanteService fabricanteService;

    private JogoService jogoService = new JogoServiceImpl();

    /**
     * Atente para os metodos assertEquals e contains (de coleções):
     * eles so funcionarão se os metodo equals e hashcode de EntidadeRaiz estiverem implementados
     */

    @Test
    public void criarTest() {
        Plataforma plataforma = this.criarPlataforma("PS3", "Sony");

        Plataforma plataformaDB = this.plataformaService.recuperar(plataforma.getId());

        Assert.assertEquals(plataforma, plataformaDB);
        Assert.assertEquals(plataforma.getFabricante(), plataformaDB.getFabricante());
    }

    @Test
    public void editarTest() {
        Plataforma plataforma = this.criarPlataforma("WIIY", "Microsoft");
        plataforma.setNome("WII");
        Fabricante fabricante = this.criarFabricante("Nintendo");
        plataforma.setFabricante(fabricante);
        this.plataformaService.editar(plataforma);

        Plataforma plataformaDB = this.plataformaService.recuperar(plataforma.getId());

        Assert.assertEquals("WII", plataformaDB.getNome());
        Assert.assertEquals(fabricante, plataformaDB.getFabricante());
    }

    @Test
    public void removerTest() {
        Plataforma plataforma = this.criarPlataforma("WII", "Nintendo");
        this.plataformaService.remover(plataforma);
        Plataforma plataformaDB = this.plataformaService.recuperar(plataforma.getId());
        Assert.assertNull(plataformaDB);
    }

    @Test
    public void listarTodosTest() {
        Plataforma ps2 = this.criarPlataforma("PS2", "Sony");
        Plataforma gameBoy = this.criarPlataforma("GameBoy", "Nintendo");

        List<Plataforma> plataformas = this.plataformaService.buscarTodos();

        Assert.assertEquals(2, plataformas.size());
        Assert.assertTrue(plataformas.contains(ps2));
        Assert.assertTrue(plataformas.contains(gameBoy));
    }

    @Test
    public void listarPorPlataformaTest() {
        Plataforma xbox360 = this.criarPlataforma("Xbox 360", "Microsoft");
        Plataforma ps3 = this.criarPlataforma("PS3", "Sony");
        Plataforma wii = this.criarPlataforma("Wii", "Nintendo");

        Jogo skyrin = this.criarJogo("The Elder Scrolls V: Skyrim", Genero.RPG, xbox360, ps3);
        Jogo fifa = this.criarJogo("Fifa", Genero.ESPORTE, xbox360, ps3, wii);

        List<Jogo> jogos = this.jogoService.buscar(ps3);
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(skyrin));
        Assert.assertTrue(jogos.contains(fifa));

        jogos = this.jogoService.buscar(wii);
        Assert.assertEquals(1, jogos.size());
        Assert.assertTrue(jogos.contains(fifa));
    }

    private Plataforma criarPlataforma(String nome, String fabricante) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        plataforma.setFabricante(this.criarFabricante(fabricante));
        this.plataformaService.criar(plataforma);
        return plataforma;
    }

    private Fabricante criarFabricante(String nome) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(nome);
        this.fabricanteService.criar(fabricante);
        return fabricante;
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

        this.jogoService.criar(jogo);
        return jogo;
    }
}
