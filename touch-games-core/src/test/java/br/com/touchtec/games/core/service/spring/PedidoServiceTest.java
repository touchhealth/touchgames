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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;

import br.com.touchtec.games.core.model.ItemPedido;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PedidoService;
import br.com.touchtec.spring.SpringBeanUtil;
import br.com.touchtec.spring.test.SpringTestUtil;
import br.com.touchtec.spring.test.TouchSpringRunner;


/**
 * @author emesquita
 */
@RunWith(TouchSpringRunner.class)
@ContextConfiguration(loader = br.com.touchtec.spring.test.SingletonContextLoader.class, locations = "classpath:/test-spring-config.xml")
public class PedidoServiceTest {

    @Autowired
    private PedidoService service;

    @Autowired
    private JogoService jogoService;

    /***/
    @Test
    @Transactional
    public void criarTest() {
        Pedido pedido = this.criarPedido(new Date(), ImmutableMap.of("Puppeteer", 1, "GTAV", 2));
        Pedido pedidoDB = this.service.recuperar(pedido.getId());
        Assert.assertEquals(pedido, pedidoDB);
    }

    /***/
    @Test
    @Transactional
    public void editarTest() {
        // Date correctDate = new GregorianCalendar(2013, 9, 13).getTime();
        // Pedido pedido = this.criarPedido(correctDate, ImmutableMap.of("StarBound", 3));
        // pedido.setData(correctDate);
        // pedido.getItens().get(0).setQuantidade(4);
        // this.service.editar(pedido);
        //
        // Pedido pedidoDB = this.service.recuperar(pedido.getId());
        //
        // Assert.assertEquals("Nintendo", pedidoDB.getNome());
        // List<Plataforma> plataformas = pedidoDB.getPlataformas();
        // Assert.assertEquals(5, plataformas.size());
        // Assert.assertTrue(plataformas.contains(this.criarPlataforma("2DS (Bolachão)")));
    }

    /***/
    @Test
    public void removerTest() {
        Pedido pedido = this.criarPedido(new Date(), ImmutableMap.of("Saints Row 4", 1));
        this.service.remover(pedido);
        Pedido pedidoDB = this.service.recuperar(pedido.getId());
        Assert.assertNull(pedidoDB);
    }

    /***/
    @Test
    @Transactional
    public void listarTodosTest() {
        Pedido rayman = this.criarPedido(new Date(), ImmutableMap.of("Rayman Legends", 2));
        Pedido amnesia = this.criarPedido(new Date(), ImmutableMap.of("Amnesia: Machine for Pigs", 1));

        List<Pedido> pedidos = this.service.listarTodos();

        Assert.assertEquals(2, pedidos.size());
        Assert.assertTrue(pedidos.contains(rayman));
        Assert.assertTrue(pedidos.contains(amnesia));
    }

    /***/
    @After
    public void after() {
        SpringTestUtil.restartContext(SpringBeanUtil.getContext());
    }

    private Pedido criarPedido(Date data, Map<String, Integer> itens) {
        Pedido pedido = new Pedido();
        pedido.setData(data);
        List<ItemPedido> intensPedido = new ArrayList<ItemPedido>();
        for (Entry<String, Integer> entry : itens.entrySet()) {
            intensPedido.add(this.criarItemPedido(entry.getKey(), entry.getValue()));
        }
        pedido.setItens(intensPedido);
        this.service.criar(pedido);
        return pedido;
    }

    private ItemPedido criarItemPedido(String nomeDoJogo, int quantidade) {
        Jogo jogo = new Jogo();
        jogo.setNome(nomeDoJogo);
        this.jogoService.criar(jogo);

        ItemPedido item = new ItemPedido();
        item.setJogo(jogo);
        item.setQuantidade(quantidade);
        return item;
    }
}
