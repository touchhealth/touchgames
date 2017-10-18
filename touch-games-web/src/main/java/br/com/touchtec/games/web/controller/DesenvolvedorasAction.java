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

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.twf.core.TWFActionSupport;

/**
 * @see "struts.xml"
 */
public class DesenvolvedorasAction extends TWFActionSupport {

    // TWFActionSupport é uma classe do TWF, que vcs aprenderao mais adiante

    private static final long serialVersionUID = 1L;

    // ########################################################################################
    // TABELA
    // ########################################################################################

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private List<Desenvolvedora> desenvolvedoras;

    @Override
    public String execute() throws Exception {
        this.desenvolvedoras = this.desenvolvedoraService.buscarTodos();
        return "jsp/admin/desenvolvedoras/list";
    }

    public List<Desenvolvedora> getDesenvolvedoras() {
        return this.desenvolvedoras;
    }


    // ########################################################################################
    // CRIACAO
    // ########################################################################################

    private String formTitle;

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/desenvolvedoras/form";
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    // ########################################################################################
    // EDICAO
    // ########################################################################################

    // Valor do <t:tablerowselector name="selectedId"> da lista
    private Long selectedId;

    // Para que form.jsp preencha a tela
    private Desenvolvedora desenvolvedora;

    public String update() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperar(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/desenvolvedoras/form";
    }

    public Desenvolvedora getDesenvolvedora() {
        return this.desenvolvedora;
    }

    public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Long getSelectedId() {
        return this.selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }


    // ########################################################################################
    // SAVE, REMOVE
    // ########################################################################################

    public String save() throws Exception {
        if (this.desenvolvedora.getId() != null) {
            this.desenvolvedoraService.editar(this.desenvolvedora);
            this.addSuccessMessage(this.desenvolvedora + " atualizada com sucesso");
        } else {
            this.desenvolvedoraService.criar(this.desenvolvedora);
            this.addSuccessMessage(this.desenvolvedora + " criada com sucesso");
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperar(this.selectedId);
        this.desenvolvedoraService.remover(this.desenvolvedora);
        this.addSuccessMessage(this.desenvolvedora + " removida com sucesso");
        return this.execute();
    }

}
