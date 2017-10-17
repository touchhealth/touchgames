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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// A partir da servlet 3.0 é possível usar anotações em vez de mapear no web.xml
@WebServlet(urlPatterns = "/hellojsp")
public class HelloJSPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Variáveis adicionadas à request podem ser recuperadas pelo JSP atravpes do operador ${}
        request.setAttribute("serverTime", new Date());

        // Em vez de escrever o código HTML diretamente na response, delegamos a tarefa a um JSP
        request.getRequestDispatcher("/jsp/servlet/result.jsp").forward(request, response);
    }

}
