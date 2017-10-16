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
import javax.persistence.EntityTransaction;
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

        // Precisa da transação por conta de Jogo -> Imagem -> byte[]
        em.getTransaction().begin();
        Jogo jogo = em.find(Jogo.class, id);
        em.getTransaction().commit();

        return jogo;
    }

    public Jogo recuperarComListas(Long id) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        Jogo jogo =  em.find(Jogo.class, id);
        Hibernate.initialize(jogo.getPlataformas());
        em.getTransaction().commit();

        return jogo;
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listar(Genero genero) {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j WHERE j.genero = :genero  ORDER BY j.nome";
        Query query = em.createQuery(queryString);
        query.setParameter("genero", genero);

        return executarTransacionalmente(query, em.getTransaction());
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listar(Plataforma plataforma) {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j WHERE :plataforma MEMBER OF j.plataformas ORDER BY j.nome";
        Query query = em.createQuery(queryString);

        Plataforma plataformaConectada = em.find(Plataforma.class, plataforma.getId());
        query.setParameter("plataforma", plataformaConectada);

        return executarTransacionalmente(query, em.getTransaction());
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listarTodos() {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j ORDER BY j.nome";
        Query query = em.createQuery(queryString);

        return executarTransacionalmente(query, em.getTransaction());
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> buscar(String nome) {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT j FROM Jogo j WHERE UPPER( j.nome) LIKE UPPER(:nome) ORDER BY j.nome";
        Query query = em.createQuery(queryString);
        String nomebusca = nome == null ? "" : nome;
        query.setParameter("nome", String.format("%%%s%%", nomebusca));

        return executarTransacionalmente(query, em.getTransaction());
    }


    /**
     * Relacionamentos eager precisam de transação para serem carregados
     */
    private List<Jogo> executarTransacionalmente(Query query, EntityTransaction transaction) {
        transaction.begin();
        List<Jogo> jogos = QueryTyper.getResultList(query);
        transaction.commit();

        return jogos;
    }
}
