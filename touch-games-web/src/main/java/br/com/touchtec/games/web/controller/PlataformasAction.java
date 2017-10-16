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

import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.FabricanteService;
import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.twf.core.TWFActionSupport;


@Component
@Scope("request")
public class PlataformasAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private FabricanteService fabricanteService;

    private String formTitle;

    private Plataforma plataforma;

    private List<Plataforma> plataformas;

    private List<Fabricante> fabricantes;

    private Long selectedId;

    @PostConstruct
    public void prepare() {
        this.fabricantes = this.fabricanteService.listarTodos();
    }

    @Override
    public String execute() throws Exception {
        this.plataformas = this.plataformaService.listarTodos();
        return "jsp/admin/plataformas/list";
    }

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/plataformas/form";
    }

    public String update() throws Exception {
        this.plataforma = this.plataformaService.recuperar(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/plataformas/form";
    }

    public String view() throws Exception {
        this.plataforma = this.plataformaService.recuperar(this.selectedId);
        return "jsp/admin/plataformas/view";
    }

    public String save() throws Exception {
        try {
            if (this.plataforma.getId() != null) {
                this.plataformaService.editar(this.plataforma);
                this.addSuccessMessage(this.plataforma + " atualizada com sucesso");
            } else {
                this.plataformaService.criar(this.plataforma);
                this.addSuccessMessage(this.plataforma + " criada com sucesso");
            }
        } catch (Exception e) {
            this.addErrorMessage(e.getMessage());
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.plataforma = this.plataformaService.recuperar(this.selectedId);
        this.plataformaService.remover(this.plataforma);
        this.addSuccessMessage(this.plataforma + " removida com sucesso");
        return this.execute();
    }

    public Plataforma getPlataforma() {
        return this.plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public List<Plataforma> getPlataformas() {
        return this.plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
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

    public List<Fabricante> getFabricantes() {
        return this.fabricantes;
    }
}
