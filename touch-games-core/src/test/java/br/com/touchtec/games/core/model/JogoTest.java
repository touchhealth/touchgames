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


import junit.framework.Assert;

import org.junit.Test;


/**
 * @author emesquita
 */
public class JogoTest {

    /***/
    @Test
    public void precoComDescontoTest() {
        Jogo jogo = new Jogo();
        Assert.assertEquals(0f, jogo.getPrecoComDesconto());

        jogo.setPreco(100f);
        Assert.assertEquals(100f, jogo.getPrecoComDesconto());

        jogo.setDesconto(10);
        Assert.assertEquals(90f, jogo.getPrecoComDesconto());

        jogo.setDesconto(200);
        Assert.assertEquals(0f, jogo.getPrecoComDesconto());

        jogo.setDesconto(-10);
        Assert.assertEquals(110f, jogo.getPrecoComDesconto());

        jogo.setPreco(0f);
        Assert.assertEquals(0f, jogo.getPrecoComDesconto());

        jogo.setDesconto(50);
        Assert.assertEquals(0f, jogo.getPrecoComDesconto());

        jogo.setPreco(100.1f);
        jogo.setDesconto(99);
        // se nao descartasse depois do segundo decimal seria 1.001
        Assert.assertEquals(1f, jogo.getPrecoComDesconto());
    }
}
