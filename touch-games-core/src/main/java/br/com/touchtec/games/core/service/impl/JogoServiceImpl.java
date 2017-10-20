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

package br.com.touchtec.games.core.service.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.persistence.QueryTyper;


public class JogoServiceImpl implements JogoService {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("touch-games");

    @Override
    public void criar(Jogo jogo) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.persist(jogo);
        em.getTransaction().commit();
    }

    @Override
    public void remover(Jogo jogo) {
        EntityManager em = EMF.createEntityManager();

        Jogo connectedEntity = em.find(Jogo.class, jogo.getId());
        if (connectedEntity == null) {
            return;
        }

        em.getTransaction().begin();
        em.remove(connectedEntity);
        em.getTransaction().commit();
    }

    @Override
    public Jogo editar(Jogo jogo) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        Jogo editado = em.merge(jogo);
        em.getTransaction().commit();

        return editado;
    }

    @Override
    public Jogo recuperar(Long id) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();

        // Necessita da transação aberta por conta de Jogo -> Imagem -> byte[]
        Jogo jogo = em.find(Jogo.class, id);

        if (jogo == null) {
            em.getTransaction().commit();
            return null;
        }

        // Necessita da transaçao aberta
        Hibernate.initialize(jogo.getPlataformas());

        em.getTransaction().commit();

        return jogo;
    }

    @Override
    public List<Jogo> buscarTodos() {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j ORDER BY j.nome";
        Query query = em.createQuery(queryString);

        em.getTransaction().begin();
        List<Jogo> jogos = QueryTyper.getResultList(query);
        em.getTransaction().commit();

        return jogos;
    }

    @Override
    public List<Jogo> buscar(Genero genero) {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j WHERE j.genero = :genero  ORDER BY j.nome";
        Query query = em.createQuery(queryString);
        query.setParameter("genero", genero);

        em.getTransaction().begin();
        List<Jogo> jogos = QueryTyper.getResultList(query);
        em.getTransaction().commit();

        return jogos;
    }

    @Override
    public List<Jogo> buscar(Plataforma plataforma) {
        // EXERCICIO
        return null;
    }

    @Override
    public List<Jogo> buscar(String nome) {
        // EXERCICIO
        return null;
    }

}
