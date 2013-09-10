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

import br.com.touchtec.games.core.model.Pedido;


/**
 * @author emesquita
 */
public interface PedidoService {

    /**
     * @param pedido
     */
    void criar(Pedido pedido);

    /**
     * @param pedido
     */
    void remover(Pedido pedido);

    /**
     * @param pedido
     * @return O pedido editado.
     */
    Pedido editar(Pedido pedido);

    /**
     * @param id
     * @return O pedido procurado.
     */
    Pedido recuperar(Long id);

    /**
     * @param id
     * @return O pedido procurado.
     */
    Pedido recuperarComListas(Long id);

    /**
     * @return lista todos
     */
    List<Pedido> listarTodos();
}
