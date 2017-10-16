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
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;
import br.com.touchtec.games.web.CarrinhoDeCompras;
import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @see "struts.xml"
 */
@Controller
@Scope("request")
public class CarrinhoAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    // Lembre-se, JogoService não foi implementado como um bean Spring.
    // Logo, não podemos injetá-lo com @Autowired
    private JogoService jogoService = new JogoServiceImpl();

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private CarrinhoDeCompras carrinhoDeCompras;

    private Long jogoId;

    private Integer quantidade;

    private Long plataformaId;

    private Integer indexItem;

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

    public String remover() throws Exception {
        this.carrinhoDeCompras.removeItem(this.indexItem);
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

    public Integer getIndexItem() {
        return this.indexItem;
    }

    public void setIndexItem(Integer indexItem) {
        this.indexItem = indexItem;
    }
}
