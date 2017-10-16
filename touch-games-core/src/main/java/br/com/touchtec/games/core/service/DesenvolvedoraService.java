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


public interface DesenvolvedoraService {

    void criar(Desenvolvedora desenvolvedora);

    void remover(Desenvolvedora desenvolvedora);

    void editar(Desenvolvedora desenvolvedora);

    Desenvolvedora recuperar(Long id);

    Desenvolvedora recuperarComListas(Long id);

    List<Desenvolvedora> listarTodos();
}
