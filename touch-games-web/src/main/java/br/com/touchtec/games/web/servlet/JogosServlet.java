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

package br.com.touchtec.games.web.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// EXERCICIO
public class JogosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // EXERCICIO

    /**
     * [GET]
     * De uma maneira simplificada, é usado para operações que NAO modificam o estado da aplicação.
     * Que sao idempotentes. Você pode chamar várias vezes e a resposta será a mesma.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // EXERCICIO
    }

    /**
     * [POST]
     * É usado para operações que modificam o estado da aplicação.
     * Que NAO sao idempotentes. Cada vez que vc chama, a resposta pode variar.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // EXERCICIO
    }


}
