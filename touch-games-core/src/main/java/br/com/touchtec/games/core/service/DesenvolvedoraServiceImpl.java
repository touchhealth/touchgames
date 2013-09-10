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

package br.com.touchtec.games.core.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.touchtec.games.core.model.Desenvolvedora;


/**
 * @author bbviana
 */
public class DesenvolvedoraServiceImpl implements DesenvolvedoraService {

    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("touch-games");

    private EntityManager em = EM_FACTORY.createEntityManager();

    @Override
    public void criar(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();

        this.em.persist(desenvolvedora);

        this.em.getTransaction().commit();
    }

    @Override
    public void remover(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();

        this.em.remove(desenvolvedora);

        this.em.getTransaction().commit();
    }

    @Override
    public void editar(Desenvolvedora desenvolvedora) {
        this.em.getTransaction().begin();

        this.em.merge(desenvolvedora);

        this.em.getTransaction().commit();
    }

    @Override
    public Desenvolvedora recuperar(Long id) {
        Desenvolvedora desenvolvedora = this.em.find(Desenvolvedora.class, id);
        return desenvolvedora;
    }

    @Override
    public Desenvolvedora recuperarComListas(Long id) {
        Desenvolvedora desenvolvedora = this.recuperar(id);
        Hibernate.initialize(desenvolvedora.getJogos());
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
