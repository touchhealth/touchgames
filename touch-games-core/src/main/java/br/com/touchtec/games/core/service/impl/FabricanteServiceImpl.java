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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.service.FabricanteService;


@Component
@Scope("singleton")
@Transactional
public class FabricanteServiceImpl implements FabricanteService {

    // equivalente ao @Autowired
    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Fabricante fabricante) {
        this.em.persist(fabricante);
    }

    @Override
    public void remover(Fabricante fabricante) {
        Fabricante connectedEntity = this.recuperar(fabricante.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
    }

    @Override
    public Fabricante editar(Fabricante fabricante) {
        return this.em.merge(fabricante);
    }

    // Quando não houver modificações de dados, use readOnly = true: melhor desempenho.
    @Transactional(readOnly = true)
    @Override
    public Fabricante recuperar(Long id) {
        return this.em.find(Fabricante.class, id);
    }

    @Transactional(readOnly = true)
    public List<Fabricante> buscarTodos() {
        String queryString = "SELECT f FROM Fabricante f ORDER BY f.nome";
        Query query = this.em.createQuery(queryString);
        List<Fabricante> list = query.getResultList();
        return list;
    }

}
