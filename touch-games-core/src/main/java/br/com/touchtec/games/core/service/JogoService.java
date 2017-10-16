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

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;


public interface JogoService {

    void criar(Jogo jogo);

    void remover(Jogo jogo);

    Jogo editar(Jogo jogo);

    Jogo recuperar(Long id);

    Jogo recuperarComListas(Long id);

    List<Jogo> listar(Genero genero);

    List<Jogo> listar(Plataforma plataforma);

    List<Jogo> listarTodos();

    List<Jogo> buscar(String nome);
}
