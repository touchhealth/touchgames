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

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.service.FabricanteService;
import br.com.touchtec.twf.core.TWFActionSupport;

/**
 * @see "struts.xml"
 */
// EXERCICIO
public class FabricantesAction extends TWFActionSupport {

    // EXERCICIO
    private FabricanteService fabricanteService;

    private String formTitle;

    private Fabricante fabricante;

    private List<Fabricante> fabricantes;

    private Long selectedId;

    @Override
    public String execute() throws Exception {
        this.fabricantes = this.fabricanteService.buscarTodos();
        return "jsp/admin/fabricantes/list";
    }

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/fabricantes/form";
    }

    public String update() throws Exception {
        this.fabricante = this.fabricanteService.recuperar(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/fabricantes/form";
    }

    public String save() throws Exception {
        if (this.fabricante.getId() != null) {
            this.fabricanteService.editar(this.fabricante);
            this.addSuccessMessage(this.fabricante + " atualizado com sucesso");
        } else {
            this.fabricanteService.criar(this.fabricante);
            this.addSuccessMessage(this.fabricante + " criado com sucesso");
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.fabricante = this.fabricanteService.recuperar(this.selectedId);
        this.fabricanteService.remover(this.fabricante);
        this.addSuccessMessage(this.fabricante + " removido com sucesso");
        return this.execute();
    }

    public Fabricante getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public List<Fabricante> getFabricantes() {
        return this.fabricantes;
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

    private static final long serialVersionUID = 1L;
}
