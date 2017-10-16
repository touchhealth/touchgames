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
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.PedidoService;


@Component
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Pedido pedido) {
        LOGGER.info("Pedido criado");
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

    @Override
    public Pedido recuperar(Long id) {
        return this.em.find(Pedido.class, id);
    }

    @Override
    public Pedido recuperarComListas(Long id) {
        Pedido pedido = this.recuperar(id);
        Hibernate.initialize(pedido.getItens());
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
