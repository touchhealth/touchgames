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
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.spring.SpringBeanUtil;
import br.com.touchtec.spring.test.SpringTestUtil;
import br.com.touchtec.spring.test.TouchSpringRunner;


/**
 * @author emesquita
 */
@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = br.com.touchtec.spring.test.SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class PlataformaServiceTest {

    @Autowired
    private PlataformaService service;

    /***/
    @Test
    public void criarTest() {
        Plataforma plataforma = this.criarPlataforma("PS3", "Sony");
        Plataforma plataformaDB = this.service.recuperar(plataforma.getId());
        Assert.assertEquals(plataforma, plataformaDB);

        Fabricante fabricante = plataformaDB.getFabricante();
        Assert.assertEquals(fabricante, this.criarFabricante("Sony"));
    }

    /***/
    @Test
    public void editarTest() {
        Plataforma plataforma = this.criarPlataforma("WIIY", "Microsoft");
        plataforma.setNome("WII");
        Fabricante fabricante = this.criarFabricante("Nintendo");
        plataforma.setFabricante(fabricante);
        this.service.editar(plataforma);

        Plataforma plataformaDB = this.service.recuperar(plataforma.getId());

        Assert.assertEquals("WII", plataformaDB.getNome());
        Assert.assertEquals(fabricante, plataformaDB.getFabricante());
    }

    /***/
    @Test
    public void removerTest() {
        Plataforma plataforma = this.criarPlataforma("WII", "Nintendo");
        this.service.remover(plataforma);
        Plataforma plataformaDB = this.service.recuperar(plataforma.getId());
        Assert.assertNull(plataformaDB);
    }

    /***/
    @Test
    @Transactional
    public void listarTodosTest() {
        Plataforma ps2 = this.criarPlataforma("PS2", "Sony");
        Plataforma gameBoy = this.criarPlataforma("GameBoy", "Nintendo");

        List<Plataforma> plataformas = this.service.listarTodos();
        Assert.assertEquals(2, plataformas.size());
        Assert.assertTrue(plataformas.contains(ps2));
        Assert.assertTrue(plataformas.contains(gameBoy));
    }

    /***/
    @After
    public void after() {
        SpringTestUtil.restartContext(SpringBeanUtil.getContext());
    }

    private Plataforma criarPlataforma(String nome, String fabricante) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        plataforma.setFabricante(this.criarFabricante(fabricante));
        this.service.criar(plataforma);
        return plataforma;
    }

    private Fabricante criarFabricante(String nome) {
        if (nome == null) {
            return null;
        }
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(nome);
        return fabricante;
    }
}
