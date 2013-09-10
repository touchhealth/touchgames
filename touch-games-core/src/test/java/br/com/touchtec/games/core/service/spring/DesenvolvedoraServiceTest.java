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


import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.spring.SpringBeanUtil;
import br.com.touchtec.spring.test.SpringTestUtil;
import br.com.touchtec.spring.test.TouchSpringRunner;


/**
 * @author emesquita
 */
@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = br.com.touchtec.spring.test.SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class DesenvolvedoraServiceTest {

    @Autowired
    private DesenvolvedoraService service;

    @Autowired
    private JogoService jogoService;

    /***/
    @Test
    public void criarTest() {
        Desenvolvedora desenvolvedora = this.criarDesenvolvedora("Bethesda Game Studios");
        Jogo fallout = this.criarJogo("Fallout 3", desenvolvedora);
        Jogo skyrim = this.criarJogo("The Elder Scrolls V: Skyrim", desenvolvedora);

        Desenvolvedora desenvolvedoraDB = this.service.recuperarComListas(desenvolvedora.getId());
        Assert.assertEquals(desenvolvedora, desenvolvedoraDB);

        List<Jogo> jogos = desenvolvedoraDB.getJogos();
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(fallout));
        Assert.assertTrue(jogos.contains(skyrim));
    }

    /***/
    @Test
    public void editarTest() {
        Desenvolvedora desenvolvedora = this.criarDesenvolvedora("Nipon Ichi");
        Jogo disgaea = this.criarJogo("Disgaea D2", desenvolvedora);
        Jogo gfp = this.criarJogo("The Guided Fate Paradox", desenvolvedora);

        desenvolvedora = this.service.recuperarComListas(desenvolvedora.getId());
        desenvolvedora.setNome("Nippon Ichi");
        Jogo zettaiHero = this.criarJogo("Zettai Hero Project: Unlosing Ranger VS. Darkdeath Evilman", desenvolvedora);
        List<Jogo> jogos = desenvolvedora.getJogos();
        jogos.add(zettaiHero);
        this.service.editar(desenvolvedora);

        Desenvolvedora desenvolvedoraDB = this.service.recuperarComListas(desenvolvedora.getId());

        Assert.assertEquals(desenvolvedora, desenvolvedoraDB);
        jogos = desenvolvedoraDB.getJogos();
        Assert.assertEquals(3, jogos.size());
        Assert.assertTrue(jogos.contains(disgaea));
        Assert.assertTrue(jogos.contains(gfp));
        Assert.assertTrue(jogos.contains(zettaiHero));
    }

    /***/
    @Test
    public void removerTest() {
        Desenvolvedora desenvolvedora = this.criarDesenvolvedora("Microsoft");
        this.service.remover(desenvolvedora);
        Desenvolvedora desenvolvedoraDB = this.service.recuperar(desenvolvedora.getId());
        Assert.assertNull(desenvolvedoraDB);
    }

    /***/
    @Test
    public void listarTodosTest() {
        Desenvolvedora ouya = this.criarDesenvolvedora("Ouya");
        Desenvolvedora sony = this.criarDesenvolvedora("Sony");

        List<Desenvolvedora> desenvolvedoras = this.service.listarTodos();

        Assert.assertEquals(2, desenvolvedoras.size());
        Assert.assertTrue(desenvolvedoras.contains(ouya));
        Assert.assertTrue(desenvolvedoras.contains(sony));
    }

    /***/
    @After
    public void after() {
        SpringTestUtil.restartContext(SpringBeanUtil.getContext());
    }

    private Desenvolvedora criarDesenvolvedora(String nome) {
        Desenvolvedora desenvolvedora = new Desenvolvedora();
        desenvolvedora.setNome(nome);
        this.service.criar(desenvolvedora);
        return desenvolvedora;
    }

    private Jogo criarJogo(String nome, Desenvolvedora desenvolvedora) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setDesenvolvedora(desenvolvedora);
        this.jogoService.criar(jogo);
        return jogo;
    }
}
