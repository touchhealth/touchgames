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

import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.PlataformaService;


public class PlataformaServiceImpl implements PlataformaService {

    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("touch-games");

    private EntityManager em = EM_FACTORY.createEntityManager();

    @Override
    public void criar(Plataforma plataforma) {
        this.em.getTransaction().begin();
        this.em.persist(plataforma);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public void remover(Plataforma plataforma) {
        this.em.getTransaction().begin();
        Plataforma connectedEntity = this.recuperar(plataforma.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public Plataforma editar(Plataforma plataforma) {
        this.em.getTransaction().begin();
        Plataforma editada = this.em.merge(plataforma);
        this.em.getTransaction().commit();
        this.em.clear();
        return editada;
    }

    @Override
    public Plataforma recuperar(Long id) {
        return this.em.find(Plataforma.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Plataforma> listarTodos() {
        String queryString = "SELECT p FROM Plataforma p ORDER BY p.nome";
        Query query = this.em.createQuery(queryString);
        List<Plataforma> list = query.getResultList();
        return list;
    }

}
