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


/**
 * Atende requisições "/hello"
 *
 * @see "web.xml"
 */
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parâmetros são passados pela url após o sinal de "?"
        // Múltiplos parâmetros são separados por "&"

        // hello?nome=Touch&sobrenome=Tecnologia

        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");

        String pagina = "" +
                "<html>" +
                "<head>" +
                "<title>Servlets</title>" +
                "</head>" +
                "<body>" +
                "<div>Olá, " + nome + sobrenome + "</div>" +
                "</body>" +
                "</html>";

        response.getWriter().write(pagina);
    }


}
