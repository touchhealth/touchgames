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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

import br.com.touchtec.games.core.model.ItemPedido;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PedidoService;
import br.com.touchtec.games.core.service.PlataformaService;
import junit.framework.Assert;


public class PedidoServiceImplTest {

    private PedidoService service = new PedidoServiceImpl();

    private JogoService jogoService = new JogoServiceImpl();

    private PlataformaService plataformaService = new PlataformaServiceImpl();

    @Test
    public void criarTest() {
        Plataforma ps3 = this.criarPlataforma("PS3");
        ItemPedido puppeteer = this.criarItemPedido("Puppeteer", 1, ps3);
        ItemPedido gtav = this.criarItemPedido("GTAV", 2, ps3);
        Date data = new GregorianCalendar(2013, 9, 13).getTime();
        Pedido pedido = this.criarPedido(data, puppeteer, gtav);

        Pedido pedidoDB = this.service.recuperarComListas(pedido.getId());
        Assert.assertEquals(pedido, pedidoDB);
    }

    @Test
    public void editarTest() {
        Plataforma pc = this.criarPlataforma("PC");
        Date correctDate = new GregorianCalendar(2013, 9, 13).getTime();
        Pedido pedido = this.criarPedido(correctDate, this.criarItemPedido("StarBound", 3, pc));

        pedido = this.service.recuperarComListas(pedido.getId());
        pedido.setData(correctDate);
        pedido.getItens().get(0).setQuantidade(4);
        Plataforma ps3 = this.criarPlataforma("PS3");
        pedido.getItens().add(this.criarItemPedido("Puppeteer", 1, ps3));
        this.service.editar(pedido);

        Pedido pedidoDB = this.service.recuperarComListas(pedido.getId());
        Assert.assertEquals(pedido, pedidoDB);
    }

    @Test
    public void removerTest() {
        Plataforma pc = this.criarPlataforma("PC");
        Pedido pedido = this.criarPedido(new Date(), this.criarItemPedido("Saints Row 4", 1, pc));
        this.service.remover(pedido);
        Pedido pedidoDB = this.service.recuperar(pedido.getId());
        Assert.assertNull(pedidoDB);
    }

    @Test
    public void listarTodosTest() {
        Plataforma pc = this.criarPlataforma("PC");
        Pedido rayman = this.criarPedido(new Date(), this.criarItemPedido("Rayman Legends", 2, pc));
        Pedido amnesia = this.criarPedido(new Date(), this.criarItemPedido("Amnesia: Machine for Pigs", 1, pc));

        List<Pedido> pedidos = this.service.listarTodos();

        Assert.assertEquals(2, pedidos.size());
        Assert.assertTrue(pedidos.contains(rayman));
        Assert.assertTrue(pedidos.contains(amnesia));
    }

    @After
    public void after() {
        Persistence.createEntityManagerFactory("touch-games");
    }

    private Pedido criarPedido(Date data, ItemPedido... itens) {
        Pedido pedido = new Pedido();
        pedido.setData(data);
        pedido.setItens(Arrays.asList(itens));
        this.service.criar(pedido);
        return pedido;
    }

    private ItemPedido criarItemPedido(String nomeDoJogo, int quantidade, Plataforma plataforma) {
        Jogo jogo = new Jogo();
        jogo.setNome(nomeDoJogo);
        this.jogoService.criar(jogo);

        ItemPedido item = new ItemPedido();
        item.setJogo(jogo);
        item.setQuantidade(quantidade);
        item.setPlataforma(plataforma);
        return item;
    }

    private Plataforma criarPlataforma(String nome) {
        Plataforma p = new Plataforma();
        p.setNome(nome);
        this.plataformaService.criar(p);
        return p;
    }
}
