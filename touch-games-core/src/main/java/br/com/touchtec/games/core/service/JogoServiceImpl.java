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


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Jogo;


/**
 * @author bbviana
 * @author emesquita
 */
@Component
@Transactional
public class JogoServiceImpl implements JogoService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Jogo jogo) {
        this.em.persist(jogo);
    }

    @Override
    public void remover(Jogo jogo) {
        Jogo connectedEntity = this.recuperar(jogo.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
    }

    @Override
    public Jogo editar(Jogo jogo) {
        return this.em.merge(jogo);
    }

    @Override
    public Jogo recuperar(Long id) {
        return this.em.find(Jogo.class, id);
    }

}
