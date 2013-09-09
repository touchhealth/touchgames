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

import br.com.touchtec.games.core.model.Desenvolvedora;


/**
 * @author bbviana
 */
public interface DesenvolvedoraService {

    /**
     * @param desenvolvedora
     */
    void criar(Desenvolvedora desenvolvedora);

    /**
     * @param desenvolvedora
     */
    void remover(Desenvolvedora desenvolvedora);

    /**
     * @param desenvolvedora
     * @return desenvolvedora
     */
    Desenvolvedora editar(Desenvolvedora desenvolvedora);

    /**
     * @param id
     * @return desenvolvedora
     */
    Desenvolvedora recuperar(Long id);

    /**
     * @return lista todos
     */
    List<Desenvolvedora> listarTodos();
}
