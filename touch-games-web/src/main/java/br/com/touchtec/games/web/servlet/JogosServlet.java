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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;

@WebServlet(urlPatterns = "/jogos/*")
public class JogosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private JogoService jogoService = new JogoServiceImpl();

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    /**
     * [GET]
     * De uma maneira simplificada, é usado para operações que NAO modificam o estado da aplicação.
     * Que sao idempotentes. Você pode chamar várias vezes e a resposta será a mesma.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = getMethod(req);
        req.setAttribute("method", method);

        // Para os selects
        List<Desenvolvedora> desenvolvedoras = desenvolvedoraService.buscarTodos();
        req.setAttribute("desenvolvedoras", desenvolvedoras);
        req.setAttribute("generos", Genero.values());

        if ("update".equals(method)) {
            Long selectedId = Long.parseLong(req.getParameter("id"));
            Jogo jogo = jogoService.recuperar(selectedId);
            req.setAttribute("jogo", jogo);
        }

        // Lista de jogos para a tabela
        List<Jogo> jogos = jogoService.buscarTodos();
        req.setAttribute("jogos", jogos);

        req.getRequestDispatcher("/jsp/servlet/jogos.jsp").forward(req, resp);
    }

    // /jogos/create => "create", pega o último texto depois da "/"
    private static String getMethod(HttpServletRequest req) {
        String[] parts = req.getRequestURI().split("/");
        return parts[parts.length - 1];
    }


    /**
     * [POST]
     * É usado para operações que modificam o estado da aplicação.
     * Que NAO sao idempotentes. Cada vez que vc chama, a resposta pode variar.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviado pelos botoes de submit
        String method = request.getParameter("method");

        if ("remove".equals(method)) {
            Jogo jogo = recuperarJogo(request);
            jogoService.remover(jogo);
        } else if ("save".equals(method)) {
            Jogo jogo = recuperarJogo(request);
            preencheJogo(request, jogo);
            jogoService.editar(jogo);
        } else if ("savenew".equals(method)) {
            Jogo jogo = new Jogo();
            preencheJogo(request, jogo);
            jogoService.criar(jogo);
        }

        // Lista de jogos para a tabela
        List<Jogo> jogos = jogoService.buscarTodos();
        request.setAttribute("jogos", jogos);

        request.getRequestDispatcher("/jsp/servlet/jogos.jsp").forward(request, response);
    }


    private Jogo recuperarJogo(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (isNullOrEmpty(id)) {
            throw new IllegalArgumentException("Id nulo");
        }
        Jogo jogo = jogoService.recuperar(Long.parseLong(id));
        return jogo;
    }

    /**
     * Preenche um Jogo com dados da request.
     * HTTP é um protocolo de texto: HyperText Transfer Protocol.
     * Por isso, precisamos converter os textos para os tipos que desejamos.
     */
    private void preencheJogo(HttpServletRequest request, Jogo jogo) {
        // STRING, não precisa de conversão

        jogo.setNome(request.getParameter("nome"));

        jogo.setDescricao(request.getParameter("descricao"));

        // ENUM

        String genero = request.getParameter("genero");
        if (isNullOrEmpty(genero)) {
            jogo.setGenero(null);
        } else {
            jogo.setGenero(Genero.valueOf(genero));
        }

        // DATA

        String dataLancamento = request.getParameter("dataLancamento");
        if (isNullOrEmpty(dataLancamento)) {
            jogo.setDataLancamento(null);
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                jogo.setDataLancamento(dateFormat.parse(dataLancamento));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        // FLOAT

        String preco = request.getParameter("preco");
        if (isNullOrEmpty(preco)) {
            jogo.setPreco(null);
        } else {
            jogo.setPreco(Float.parseFloat(preco));
        }

        // INTEGER

        String desconto = request.getParameter("desconto");
        if (isNullOrEmpty(desconto)) {
            jogo.setDesconto(0);
        } else {
            jogo.setDesconto(Integer.parseInt(desconto));
        }

        // ASSOCIACAO

        String desenvolvedoraId = request.getParameter("desenvolvedora");
        if (isNullOrEmpty(desenvolvedoraId)) {
            jogo.setDesenvolvedora(null);
        } else {
            Long id = Long.parseLong(desenvolvedoraId);
            jogo.setDesenvolvedora(desenvolvedoraService.recuperar(id));
        }
    }
}
