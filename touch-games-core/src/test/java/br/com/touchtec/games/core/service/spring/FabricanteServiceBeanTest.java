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
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.FabricanteService;
import br.com.touchtec.spring.SpringBeanUtil;
import br.com.touchtec.spring.test.SpringTestUtil;
import br.com.touchtec.spring.test.TouchSpringRunner;
import junit.framework.Assert;


@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = br.com.touchtec.spring.test.SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class FabricanteServiceBeanTest {

    @Autowired
    private FabricanteService service;

    @Test
    public void criarTest() {
        Fabricante fabricante = this.criarFabricante("Sony", "Vita", "PS3", "PSP", "PS4");

        Fabricante fabricanteDB = this.service.recuperarComListas(fabricante.getId());
        Assert.assertEquals(fabricante, fabricanteDB);

        List<Plataforma> plataformas = fabricanteDB.getPlataformas();
        Assert.assertEquals(4, plataformas.size());
        Assert.assertTrue(plataformas.contains(this.criarPlataforma("Vita")));
        Assert.assertTrue(plataformas.contains(this.criarPlataforma("PS3")));
        Assert.assertTrue(plataformas.contains(this.criarPlataforma("PSP")));
        Assert.assertTrue(plataformas.contains(this.criarPlataforma("PS4")));
    }

    @Test
    public void editarTest() {
        Fabricante fabricante = this.criarFabricante("Ñ intendo", "DS", "3DS", "WII", "WIIU");

        fabricante.setNome("Nintendo");
        List<Plataforma> plataformas = fabricante.getPlataformas();
        plataformas.add(this.criarPlataforma("2DS (Bolachão)"));
        this.service.editar(fabricante);

        Fabricante fabricanteDB = this.service.recuperarComListas(fabricante.getId());

        Assert.assertEquals("Nintendo", fabricanteDB.getNome());
        plataformas = fabricanteDB.getPlataformas();
        Assert.assertEquals(5, plataformas.size());
        Assert.assertTrue(plataformas.contains(this.criarPlataforma("2DS (Bolachão)")));
    }

    @Test
    public void removerTest() {
        Fabricante fabricante = this.criarFabricante("Microsoft");
        this.service.remover(fabricante);
        Fabricante fabricanteDB = this.service.recuperar(fabricante.getId());
        Assert.assertNull(fabricanteDB);
    }

    @Test
    public void listarTodosTest() {
        Fabricante ouya = this.criarFabricante("Ouya");
        Fabricante sony = this.criarFabricante("Sony");

        List<Fabricante> fabricantes = this.service.listarTodos();

        Assert.assertEquals(2, fabricantes.size());
        Assert.assertTrue(fabricantes.contains(ouya));
        Assert.assertTrue(fabricantes.contains(sony));
    }

    @After
    public void after() {
        SpringTestUtil.restartContext(SpringBeanUtil.getContext());
    }

    private Fabricante criarFabricante(String nome, String... nomesPlataforma) {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome(nome);
        if (ArrayUtils.isNotEmpty(nomesPlataforma)) {
            List<Plataforma> listaPlataformas = new ArrayList<Plataforma>();
            for (String nomePlataforma : nomesPlataforma) {
                listaPlataformas.add(this.criarPlataforma(nomePlataforma));
            }
            fabricante.setPlataformas(listaPlataformas);
        }
        this.service.criar(fabricante);
        return fabricante;
    }

    private Plataforma criarPlataforma(String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);
        return plataforma;
    }
}
