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
        // Funciona porque no persistence.xml setamos "hibernate.hbm2ddl.auto=create-drop"
        Persistence.createEntityManagerFactory("touch-games");
    }

    @Test
    public void criarTest() {
        // CENARIO
        Jogo jogo = this.criarJogo("Final Fantasy VIII");

        // TESTE
        Jogo jogoCriado = this.service.recuperar(jogo.getId());

        // ASSERTS
        jogoCriado = this.service.recuperar(jogoCriado.getId());
        // equals/hashcode É importante aqui
        Assert.assertEquals(jogo, jogoCriado);
    }

    @Test
    public void editarTest() {
        // CENARIO
        Jogo jogo = this.criarJogo("Final Fantasy X");

        // TESTE
        jogo.setDesconto(66);
        this.service.editar(jogo);

        // ASSERT
        Jogo jogoEditado = this.service.recuperar(jogo.getId());
        Assert.assertEquals(66, jogoEditado.getDesconto());
    }

    @Test
    public void removerTest() {
        // CENARIO
        Jogo jogo = this.criarJogo("Final Fantasy XII");

        // TESTE
        this.service.remover(jogo);

        // ASSERTS
        Jogo jogoRemovido = this.service.recuperar(jogo.getId());
        Assert.assertNull(jogoRemovido);
    }


    @Test
    public void buscarTodosTest() {
        // CENARIO
        Jogo chronoTrigger = this.criarJogo("Chrono Trigger");
        Jogo chronoCross = this.criarJogo("Chrono Cross");

        // TESTE
        List<Jogo> jogos = this.service.buscarTodos();

        // ASSERTS
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(chronoTrigger));
        Assert.assertTrue(jogos.contains(chronoCross));
    }


    @Test
    public void buscarPorNomeTest() {
        // CENARIO
        Jogo chronoTrigger = this.criarJogo("Chrono Trigger");
        Jogo chronoCross = this.criarJogo("Chrono Cross");
        this.criarJogo("Final Fantasy VII");

        // TESTE 1
        List<Jogo> jogos = this.service.buscar("HrOnO");

        // ASSERTS 1
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(chronoTrigger));
        Assert.assertTrue(jogos.contains(chronoCross));

        // TESTE 2
        jogos = this.service.buscar("Bazinga");

        // ASSERTS 2
        Assert.assertEquals(0, jogos.size());
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
