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

import br.com.touchtec.games.core.model.Plataforma;


/**
 * @author emesquita
 */
public interface PlataformaService {

    /**
     * @param plataforma
     */
    void criar(Plataforma plataforma);

    /**
     * @param plataforma
     */
    void remover(Plataforma plataforma);

    /**
     * @param plataforma
     * @return O plataforma editado.
     */
    Plataforma editar(Plataforma plataforma);

    /**
     * @param id
     * @return O plataforma procurado.
     */
    Plataforma recuperar(Long id);

    /**
     * @return lista todos
     */
    List<Plataforma> listarTodos();
}
