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

package br.com.touchtec.games.core.service;


import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.spring.test.TouchSpringRunner;


/**
 * @author emesquita
 */
@Transactional
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
        // Plataforma plataforma = this.criarPlataforma("Ñ intendo", "DS", "3DS", "WII", "WIIU");
        // plataforma.setNome("Nintendo");
        // List<Plataforma> plataformasa = plataforma.getPlataformas();
        // plataformasa.add(this.criarPlataforma("2DS (Bolachão)"));
        // this.service.editar(plataforma);
        //
        // Plataforma plataformaDB = this.service.recuperar(plataforma.getId());
        //
        // Assert.assertEquals("Nintendo", plataformaDB.getNome());
        // List<Plataforma> plataformas = plataformaDB.getPlataformas();
        // Assert.assertEquals(5, plataformas.size());
        // Assert.assertTrue(plataformas.contains(this.criarPlataforma("2DS (Bolachão)")));
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
    public void listarTodosTest() {
        Plataforma ps2 = this.criarPlataforma("PS2", "Sony");
        Plataforma gameBoy = this.criarPlataforma("GameBoy", "Nintendo");

        List<Plataforma> plataformas = this.service.listarTodos();
        Assert.assertEquals(2, plataformas.size());
        Assert.assertTrue(plataformas.contains(ps2));
        Assert.assertTrue(plataformas.contains(gameBoy));
    }

    private Plataforma criarPlataforma(String nome, String fabricante) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        plataforma.setFabricante(this.criarFabricante(fabricante));
        this.service.criar(plataforma);
        return plataforma;
    }

    private Fabricante criarFabricante(String nome) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(nome);
        return fabricante;
    }
}
