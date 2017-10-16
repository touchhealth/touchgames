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

package br.com.touchtec.games.core.model;


import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;


public class PedidoTest {

    @Test
    public void valorTotalTest() {
        Pedido pedido = this.criarPedido(new Date());
        Assert.assertEquals(0f, pedido.getValorTotal());

        ItemPedido item1 = this.criarItemPedido("Jogo1", 5, 10f, 75);
        ItemPedido item2 = this.criarItemPedido("Jogo2", 2, 20f, 30);
        pedido = this.criarPedido(new Date(), item1, item2);

        Assert.assertEquals(40.5f, pedido.getValorTotal());
    }

    private Pedido criarPedido(Date data, ItemPedido... itens) {
        Pedido pedido = new Pedido();
        pedido.setData(data);
        pedido.setItens(Arrays.asList(itens));
        return pedido;
    }

    private ItemPedido criarItemPedido(String nomeDoJogo, int quantidade, float preco, int desconto) {
        Jogo jogo = new Jogo();
        jogo.setNome(nomeDoJogo);
        jogo.setPreco(preco);
        jogo.setDesconto(desconto);

        ItemPedido item = new ItemPedido();
        item.setJogo(jogo);
        item.setQuantidade(quantidade);
        return item;
    }
}
