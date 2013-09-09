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

package br.com.touchtec.games.core.service.spring;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.PedidoService;


/**
 * @author emesquita
 */
@Component
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

    @Override
    public Pedido recuperar(Long id) {
        return this.em.find(Pedido.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> listarTodos() {
        String queryString = "SELECT p FROM Pedido p ORDER BY p.data";
        Query query = this.em.createQuery(queryString);
        List<Pedido> list = query.getResultList();
        return list;
    }

}
