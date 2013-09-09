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

import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.PlataformaService;


/**
 * @author emesquita
 */
@Component
@Transactional
public class PlataformaServiceImpl implements PlataformaService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Plataforma plataforma) {
        this.em.persist(plataforma);
    }

    @Override
    public void remover(Plataforma plataforma) {
        Plataforma connectedEntity = this.recuperar(plataforma.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
    }

    @Override
    public Plataforma editar(Plataforma plataforma) {
        return this.em.merge(plataforma);
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
