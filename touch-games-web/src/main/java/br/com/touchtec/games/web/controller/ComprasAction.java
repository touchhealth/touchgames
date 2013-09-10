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

package br.com.touchtec.games.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.web.CarrinhoDeCompras;
import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @author filipe
 */
@Controller
@Scope("request")
public class ComprasAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarrinhoDeCompras carrinhoDeCompras;

    @Autowired
    private JogoService jogoService;

    private List<Jogo> jogosDestaques;

    private Jogo jogoSelecionado;

    @Override
    public String execute() throws Exception {
        this.jogosDestaques = this.jogoService.listarTodos();
        return "jsp/home";
    }

    public String jogoDetalhes() throws Exception {
        this.jogoSelecionado = this.jogoService.recuperar(this.jogoSelecionado.getId());
        return "jsp/jogo_detalhes";
    }

    public String finalizarCompra() throws Exception {
        this.carrinhoDeCompras.clear();
        return "jsp/sucesso";
    }

    public List<Jogo> getJogosDestaques() {
        return this.jogosDestaques;
    }

    public Jogo getJogoSelecionado() {
        return this.jogoSelecionado;
    }

    public void setJogoSelecionado(Jogo jogoSelecionado) {
        this.jogoSelecionado = jogoSelecionado;
    }

}
