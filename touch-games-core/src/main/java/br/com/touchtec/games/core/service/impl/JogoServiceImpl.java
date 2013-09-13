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


/**
 * @author emesquita
 */
public class JogoServiceImpl implements JogoService {

    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("touch-games");

    private EntityManager em = EM_FACTORY.createEntityManager();

    @Override
    public void criar(Jogo jogo) {
        this.em.getTransaction().begin();
        this.em.persist(jogo);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public void remover(Jogo jogo) {
        this.em.getTransaction().begin();
        Jogo connectedEntity = this.recuperar(jogo.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public Jogo editar(Jogo jogo) {
        this.em.getTransaction().begin();
        Jogo editado = this.em.merge(jogo);
        this.em.getTransaction().commit();
        this.em.clear();
        return editado;
    }

    @Override
    public Jogo recuperar(Long id) {
        return this.em.find(Jogo.class, id);
    }

    public Jogo recuperarComListas(Long id) {
        this.em.getTransaction().begin();
        Jogo jogo = this.recuperar(id);
        Hibernate.initialize(jogo.getPlataformas());
        this.em.getTransaction().commit();
        this.em.clear();
        return jogo;
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listar(Genero genero) {
        String queryString = "SELECT j FROM Jogo j WHERE j.genero = :genero  ORDER BY j.nome";
        Query query = this.em.createQuery(queryString);
        query.setParameter("genero", genero);
        List<Jogo> list = query.getResultList();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listar(Plataforma plataforma) {
        Plataforma plataformaConectada = this.em.find(Plataforma.class, plataforma.getId());

        String queryString = "SELECT j FROM Jogo j WHERE :plataforma IN ELEMENTS(j.plataformas)  ORDER BY j.nome";
        Query query = this.em.createQuery(queryString);
        query.setParameter("plataforma", plataformaConectada);
        List<Jogo> list = query.getResultList();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> listarTodos() {
        String queryString = "SELECT j FROM Jogo j ORDER BY j.nome";
        Query query = this.em.createQuery(queryString);
        this.em.getTransaction().begin();
        List<Jogo> list = query.getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Jogo> buscar(String nome) {
        String queryString = "SELECT j FROM Jogo j WHERE UPPER( j.nome) LIKE UPPER(:nome) ORDER BY j.nome";
        Query query = this.em.createQuery(queryString);
        String nomebusca = nome == null ? "" : nome;
        query.setParameter("nome", String.format("%%%s%%", nomebusca));

        List<Jogo> list = query.getResultList();
        return list;
    }
}
