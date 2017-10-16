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

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;


@Component
@Transactional
public class DesenvolvedoraServiceBean implements DesenvolvedoraService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void criar(Desenvolvedora desenvolvedora) {
        this.em.persist(desenvolvedora);
    }

    @Override
    public void remover(Desenvolvedora desenvolvedora) {
        Desenvolvedora connectedEntity = this.recuperar(desenvolvedora.getId());
        if (connectedEntity == null) {
            return;
        }
        this.em.remove(connectedEntity);
    }

    @Override
    public void editar(Desenvolvedora desenvolvedora) {
        this.em.merge(desenvolvedora);
    }

    @Override
    public Desenvolvedora recuperar(Long id) {
        return this.em.find(Desenvolvedora.class, id);
    }

    @Override
    public Desenvolvedora recuperarComListas(Long id) {
        Desenvolvedora desenvolvedora = this.recuperar(id);
        Hibernate.initialize(desenvolvedora.getJogos());
        return desenvolvedora;
    }

    @SuppressWarnings("unchecked")
    public List<Desenvolvedora> listarTodos() {
        String queryString = "SELECT d FROM Desenvolvedora d ORDER BY d.nome";
        Query query = this.em.createQuery(queryString);
        List<Desenvolvedora> list = query.getResultList();
        return list;
    }
}
