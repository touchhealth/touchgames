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


import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Imagem;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;
import br.com.touchtec.twf.core.TWFActionSupport;

/**
 * @see "struts.xml"
 */
@Component
@Scope("request")
public class JogosAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    // Lembre-se, JogoService não foi implementado como um bean Spring.
    // Logo, não podemos injetá-lo com @Autowired
    private JogoService jogoService = new JogoServiceImpl();

    private String formTitle;

    private Jogo jogo;

    private List<File> imagens;

    private List<Jogo> jogos;

    private DesenvolvedoraServiceImpl desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private List<Desenvolvedora> desenvolvedoras;

    @Autowired
    private PlataformaService plataformaService;

    private List<Plataforma> plataformas;

    private Long selectedId;

    @PostConstruct
    public void prepare() {
        this.desenvolvedoras = this.desenvolvedoraService.buscarTodos();
        this.plataformas = this.plataformaService.buscarTodos();
    }

    @Override
    public String execute() throws Exception {
        this.jogos = this.jogoService.buscarTodos();
        return "jsp/admin/jogos/list";
    }

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/jogos/form";
    }

    public String update() throws Exception {
        this.jogo = this.jogoService.recuperar(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/jogos/form";
    }

    public String view() throws Exception {
        this.jogo = this.jogoService.recuperar(this.selectedId);
        return "jsp/admin/jogos/view";
    }

    public String save() throws Exception {
        if (CollectionUtils.isNotEmpty(this.imagens)) {
            for (File imagem : this.imagens) {
                FileInputStream inputStream = new FileInputStream(imagem);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                this.jogo.getImagens().add(new Imagem(bytes));
            }
        }

        if (this.jogo.getId() != null) {
            this.jogoService.editar(this.jogo);
            this.addSuccessMessage(this.jogo + " atualizado com sucesso");
        } else {
            this.jogoService.criar(this.jogo);
            this.addSuccessMessage(this.jogo + " criado com sucesso");
        }

        return this.execute();
    }

    public String remove() throws Exception {
        this.jogo = this.jogoService.recuperar(this.selectedId);
        this.jogoService.remover(this.jogo);
        this.addSuccessMessage(this.jogo + " removido com sucesso");
        return this.execute();
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<File> getImagens() {
        return this.imagens;
    }

    public void setImagens(List<File> imagens) {
        this.imagens = imagens;
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
