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

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.persistence.QueryTyper;


public class DesenvolvedoraServiceImpl implements DesenvolvedoraService {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("touch-games");

    @Override
    public void criar(Desenvolvedora desenvolvedora) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.persist(desenvolvedora);
        em.getTransaction().commit();
    }

    @Override
    public void remover(Desenvolvedora desenvolvedora) {
        EntityManager em = EMF.createEntityManager();

        Desenvolvedora connectedEntity = em.find(Desenvolvedora.class, desenvolvedora.getId());
        if (connectedEntity == null) {
            return;
        }

        em.getTransaction().begin();
        em.remove(connectedEntity);
        em.getTransaction().commit();
    }

    @Override
    public void editar(Desenvolvedora desenvolvedora) {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();
        em.merge(desenvolvedora);
        em.getTransaction().commit();
    }

    @Override
    public Desenvolvedora recuperar(Long id) {
        EntityManager em = EMF.createEntityManager();

        Desenvolvedora desenvolvedora = em.find(Desenvolvedora.class, id);

        em.getTransaction().begin();

        // Necessita da transação aberta
        Hibernate.initialize(desenvolvedora.getJogos());

        em.getTransaction().commit();

        return desenvolvedora;
    }

    @Override
    public List<Desenvolvedora> buscarTodos() {
        EntityManager em = EMF.createEntityManager();

        String queryString = "SELECT d FROM Desenvolvedora d ORDER BY d.nome";
        Query query = em.createQuery(queryString);
        List<Desenvolvedora> desenvolvedoras = QueryTyper.getResultList(query);
        return desenvolvedoras;
    }
}
