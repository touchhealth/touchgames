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
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
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

    private String formTitle;

    private Jogo jogo;

    private List<Jogo> jogos;

    @Autowired
    private DesenvolvedoraService desenvolvedoraService;

    private List<Desenvolvedora> desenvolvedoras;

    @Autowired
    private PlataformaService plataformaService;

    private List<Plataforma> plataformas;

    private Long selectedId;

    @PostConstruct
    public void prepare() {
        this.desenvolvedoras = this.desenvolvedoraService.listarTodos();
        this.plataformas = this.plataformaService.listarTodos();
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
        this.jogo = this.jogoService.recuperarComListas(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/jogos/form";
    }

    public String view() throws Exception {
        this.jogo = this.jogoService.recuperarComListas(this.selectedId);
        return "jsp/admin/jogos/view";
    }

    public String save() throws Exception {
        if (this.jogo.getId() != null) {
            this.jogoService.editar(this.jogo);
        } else {
            this.jogoService.criar(this.jogo);
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.jogo = this.jogoService.recuperar(this.selectedId);
        this.jogoService.remover(this.jogo);
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

    public List<Plataforma> getPlataformas() {
        return this.plataformas;
    }

}
