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


public class DesenvolvedoraServiceImpl implements DesenvolvedoraService {

    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("touch-games");

    private EntityManager em = EM_FACTORY.createEntityManager();

    @Override
    public void criar(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();
        this.em.persist(desenvolvedora);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public void remover(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();
        Desenvolvedora connectedEntity = this.recuperar(desenvolvedora.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public void editar(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();
        this.em.merge(desenvolvedora);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public Desenvolvedora recuperar(Long id) {
        Desenvolvedora desenvolvedora = this.em.find(Desenvolvedora.class, id);
        return desenvolvedora;
    }

    @Override
    public Desenvolvedora recuperarComListas(Long id) {
        this.em.getTransaction().begin();
        Desenvolvedora desenvolvedora = this.recuperar(id);
        Hibernate.initialize(desenvolvedora.getJogos());
        this.em.getTransaction().commit();
        this.em.clear();
        return desenvolvedora;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Desenvolvedora> listarTodos() {
        String queryString = "SELECT d FROM Desenvolvedora d ORDER BY d.nome";
        Query query = this.em.createQuery(queryString);
        List<Desenvolvedora> list = query.getResultList();
        return list;
    }
}
