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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.games.web.CarrinhoDeCompras;
import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @author filipe
 */
@Controller
@Scope("request")
public class CarrinhoAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private CarrinhoDeCompras carrinhoDeCompras;

    private Long jogoId;

    private Integer quantidade;

    private Long plataformaId;

    @Override
    public String execute() throws Exception {
        return "jsp/carrinho_de_compras";
    }

    public String adicionar() throws Exception {
        Jogo jogo = this.jogoService.recuperar(this.jogoId);
        Plataforma plataforma = this.plataformaService.recuperar(this.plataformaId);
        this.carrinhoDeCompras.addItem(jogo, this.quantidade, plataforma);
        return this.execute();
    }

    public Long getJogoId() {
        return this.jogoId;
    }

    public void setJogoId(Long jogoId) {
        this.jogoId = jogoId;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getPlataformaId() {
        return this.plataformaId;
    }

    public void setPlataformaId(Long plataformaId) {
        this.plataformaId = plataformaId;
    }

    public CarrinhoDeCompras getCarrinhoDeCompras() {
        return this.carrinhoDeCompras;
    }

}