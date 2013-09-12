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

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.PedidoService;


/**
 * @author emesquita
 */
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class);
    private static final EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("touch-games");

    private EntityManager em = EM_FACTORY.createEntityManager();

    @Override
    public void criar(Pedido pedido) {
        this.em.getTransaction().begin();
        LOGGER.info("Pedido criado");
        this.em.persist(pedido);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public void remover(Pedido pedido) {
        this.em.getTransaction().begin();
        Pedido connectedEntity = this.recuperar(pedido.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
        this.em.getTransaction().commit();
        this.em.clear();
    }

    @Override
    public Pedido editar(Pedido pedido) {
        this.em.getTransaction().begin();
        Pedido editado = this.em.merge(pedido);
        this.em.getTransaction().commit();
        this.em.clear();
        return editado;
    }

    @Override
    public Pedido recuperar(Long id) {
        return this.em.find(Pedido.class, id);
    }

    @Override
    public Pedido recuperarComListas(Long id) {
        this.em.getTransaction().begin();
        Pedido pedido = this.recuperar(id);
        Hibernate.initialize(pedido.getItens());
        this.em.getTransaction().commit();
        this.em.clear();
        return pedido;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> listarTodos() {
        String queryString = "SELECT p FROM Pedido p ORDER BY p.data";
        Query query = this.em.createQuery(queryString);
        List<Pedido> list = query.getResultList();

        // Adicionado para poder usar o valor total. Bom exercicio
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        for (Pedido pedido : list) {
            Hibernate.initialize(pedido.getItens());
        }
        // Adicionado para poder usar o valor total. Bom exercicio

        return list;
    }
}
