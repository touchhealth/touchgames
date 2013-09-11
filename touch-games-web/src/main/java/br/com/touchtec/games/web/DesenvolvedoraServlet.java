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

package br.com.touchtec.games.web;


import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;


/**
 * @author bbviana
 */
public class DesenvolvedoraServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String page = "<html><head><title>Desenvolvedoras</title></head><body> Hello! </body></html>";
        // resp.getWriter().write(page);

        DesenvolvedoraService service = new DesenvolvedoraServiceImpl();

        List<Desenvolvedora> desenvolvedoras = service.listarTodos();
        req.setAttribute("desenvolvedoras", desenvolvedoras);

        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String method = parts[parts.length - 1];
        req.setAttribute("method", method);

        String selectedId = req.getParameter("id");
        if (selectedId != null) {
            Long selectedIdAsLong = Long.parseLong(selectedId);
            Desenvolvedora desenvolvedora = service.recuperar(selectedIdAsLong);
            req.setAttribute("desenvolvedora", desenvolvedora);
        }

        req.getRequestDispatcher("/jsp/servlet/desenvolvedoras.jsp").forward(req, resp);
    }

    // save e remove
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DesenvolvedoraService service = new DesenvolvedoraServiceImpl();

        String id = req.getParameter("id");

        Desenvolvedora desenvolvedora;
        if (isNullOrEmpty(id)) {
            desenvolvedora = new Desenvolvedora();
        } else {
            Long idAsLong = Long.parseLong(id);
            desenvolvedora = service.recuperar(idAsLong);
        }

        String method = req.getParameter("method");

        if ("remove".equals(method)) {
            service.remover(desenvolvedora);
        } else if ("save".equals(method)) {
            String nome = req.getParameter("nome");
            desenvolvedora.setNome(nome);
            service.editar(desenvolvedora);
        } else if ("savenew".equals(method)) {
            String nome = req.getParameter("nome");
            desenvolvedora.setNome(nome);
            service.criar(desenvolvedora);
        }

        List<Desenvolvedora> desenvolvedoras = service.listarTodos();
        req.setAttribute("desenvolvedoras", desenvolvedoras);

        req.getRequestDispatcher("/jsp/servlet/desenvolvedoras.jsp").forward(req, resp);
    }

}
