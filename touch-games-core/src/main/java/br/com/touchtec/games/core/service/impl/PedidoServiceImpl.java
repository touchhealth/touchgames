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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.PedidoService;
import br.com.touchtec.persistence.QueryTyper;


@Component
@Scope("singleton")
@Transactional
public class PedidoServiceImpl implements PedidoService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Pedido pedido) {
        this.em.persist(pedido);
    }

    @Override
    public void remover(Pedido pedido) {
        Pedido connectedEntity = this.recuperar(pedido.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return this.em.merge(pedido);
    }

    @Transactional(readOnly = true)
    @Override
    public Pedido recuperar(Long id) {
        Pedido pedido = this.em.find(Pedido.class, id);
        Hibernate.initialize(pedido.getItens());
        return pedido;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> buscarTodos() {
        String queryString = "SELECT p FROM Pedido p ORDER BY p.data";
        Query query = this.em.createQuery(queryString);
        List<Pedido> pedidos = QueryTyper.getResultList(query);

        // Adicionado para poder usar o valor total
        if (CollectionUtils.isEmpty(pedidos)) {
            return pedidos;
        }
        for (Pedido pedido : pedidos) {
            Hibernate.initialize(pedido.getItens());
        }
        // Adicionado para poder usar o valor total

        return pedidos;
    }
}
