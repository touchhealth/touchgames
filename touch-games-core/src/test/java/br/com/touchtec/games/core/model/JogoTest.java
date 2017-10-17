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


import org.junit.Test;

import junit.framework.Assert;


public class JogoTest {

    @Test
    public void precoComDescontoTest() {
        // CENARIO
        Jogo jogo = new Jogo();
        jogo.setPreco(100f);
        jogo.setDesconto(10);

        // TESTE
        Float precoComDesconto = jogo.getPrecoComDesconto();

        // ASSERTS
        Assert.assertEquals(90f, precoComDesconto);
    }
}
