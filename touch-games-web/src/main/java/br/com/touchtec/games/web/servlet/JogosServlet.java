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


import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;


public class JogosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JogoService service = new JogoServiceImpl();

        List<Jogo> jogos = service.listarTodos();
        req.setAttribute("jogos", jogos);

        String uri = req.getRequestURI();
        String[] parts = uri.split("/");
        String method = parts[parts.length - 1];
        req.setAttribute("method", method);

        if ("create".equals(method) || "update".equals(method)) {
            DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();
            List<Desenvolvedora> desenvolvedoras = desenvolvedoraService.listarTodos();
            req.setAttribute("desenvolvedoras", desenvolvedoras);

            req.setAttribute("generos", Genero.values());
        }

        String selectedId = req.getParameter("id");
        if (selectedId != null) {
            Long selectedIdAsLong = Long.parseLong(selectedId);
            Jogo jogo = service.recuperar(selectedIdAsLong);
            req.setAttribute("jogo", jogo);
        }

        req.getRequestDispatcher("/jsp/servlet/jogos_servlet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JogoService service = new JogoServiceImpl();

        String id = req.getParameter("id");

        Jogo jogo;
        if (isNullOrEmpty(id)) {
            jogo = new Jogo();
        } else {
            Long idAsLong = Long.parseLong(id);
            jogo = service.recuperar(idAsLong);
        }

        String method = req.getParameter("method");

        if ("remove".equals(method)) {
            service.remover(jogo);
        } else if ("save".equals(method)) {
            this.toEntity(req, jogo);
            service.editar(jogo);
        } else if ("savenew".equals(method)) {
            this.toEntity(req, jogo);
            service.criar(jogo);
        }

        List<Jogo> jogos = service.listarTodos();
        req.setAttribute("jogos", jogos);

        req.getRequestDispatcher("/jsp/servlet/jogos_servlet.jsp").forward(req, resp);
    }

    private void toEntity(HttpServletRequest req, Jogo jogo) {
        jogo.setNome(req.getParameter("nome"));
        jogo.setDescricao(req.getParameter("descricao"));

        String desenvolvedoraId = req.getParameter("desenvolvedora");
        if (StringUtils.isNotEmpty(desenvolvedoraId)) {
            DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();
            Long id = Long.parseLong(desenvolvedoraId);
            jogo.setDesenvolvedora(desenvolvedoraService.recuperar(id));
        } else {
            jogo.setDesenvolvedora(null);
        }

        String genero = req.getParameter("genero");
        if (StringUtils.isNotEmpty(genero)) {
            jogo.setGenero(Genero.valueOf(genero));
        } else {
            jogo.setGenero(null);
        }

        String dataLancamento = req.getParameter("dataLancamento");
        if (StringUtils.isNotEmpty(dataLancamento)) {
            try {
                jogo.setDataLancamento(DATE_FORMAT.parse(dataLancamento));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            jogo.setDataLancamento(null);
        }

        String preco = req.getParameter("preco");
        if (StringUtils.isNotEmpty(preco)) {
            jogo.setPreco(Float.parseFloat(preco));
        } else {
            jogo.setPreco(null);
        }

        String desconto = req.getParameter("desconto");
        if (StringUtils.isNotEmpty(desconto)) {
            jogo.setDesconto(Integer.parseInt(desconto));
        } else {
            jogo.setDesconto(0);
        }
    }
}
