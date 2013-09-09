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

package br.com.touchtec.games.web.controller;


import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @author bbviana
 */
public class HomeAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        return "jsp/home";
    }

}
