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


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import br.com.touchtec.games.core.model.Imagem;


/**
 * @author bbviana
 */
public class ImagensServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("touch-games");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");

        String imagemId = req.getParameter("id");

        if (Strings.isNullOrEmpty(imagemId)) {
            return;
        }

        Long idAsLong = Long.parseLong(imagemId);

        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Imagem imagem = em.find(Imagem.class, idAsLong);
        em.getTransaction().commit();

        byte[] bytes = imagem.getBytes();

        ServletOutputStream os = resp.getOutputStream();
        resp.getOutputStream().write(bytes);
        os.close();
    }

}
