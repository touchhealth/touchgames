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


/**
 * @author bbviana
 * @author emesquita
 */
public interface JogoService {

    /**
     * @param jogo
     */
    void criar(Jogo jogo);

    /**
     * @param jogo
     */
    void remover(Jogo jogo);

    /**
     * @param jogo
     * @return O jogo editado.
     */
    Jogo editar(Jogo jogo);

    /**
     * @param id
     * @return O jogo procurado.
     */
    Jogo recuperar(Long id);

    /**
     * @param id
     * @return O jogo procurado.
     */
    Jogo recuperarComListas(Long id);

    /**
     * @param genero
     * @return A lista de jogos por genero.
     */
    List<Jogo> listar(Genero genero);

    /**
     * @param plataforma
     * @return A lista de jogos por plataforma.
     */
    List<Jogo> listar(Plataforma plataforma);

    /**
     * @return lista todos
     */
    List<Jogo> listarTodos();

    /**
     * @param nome
     * @return O jogo procurado.
     */
    List<Jogo> buscar(String nome);
}
