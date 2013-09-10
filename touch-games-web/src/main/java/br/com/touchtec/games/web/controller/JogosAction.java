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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @author bbviana
 */
@Component
@Scope("request")
public class JogosAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JogoService jogoService;

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private String formTitle;

    private Jogo jogo;

    private List<Jogo> jogos;

    private List<Desenvolvedora> desenvolvedoras;

    private Long selectedId;

    @PostConstruct
    public void prepare() {
        this.desenvolvedoras = this.desenvolvedoraService.listarTodos();
    }

    @Override
    public String execute() throws Exception {
        this.jogos = this.jogoService.listarTodos();
        return "jsp/admin/jogos/list";
    }

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/jogos/form";
    }

    public String update() throws Exception {
        this.formTitle = "Edição";
        return "jsp/admin/jogos/form";
    }

    public String save() throws Exception {
        this.jogoService.criar(this.jogo);
        return this.execute();
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Jogo> getJogos() {
        return this.jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Long getSelectedId() {
        return this.selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public List<Desenvolvedora> getDesenvolvedoras() {
        return this.desenvolvedoras;
    }

}
