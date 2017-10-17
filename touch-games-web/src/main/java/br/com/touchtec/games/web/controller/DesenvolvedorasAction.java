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

    private static final long serialVersionUID = 1L;

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private String formTitle;

    private Desenvolvedora desenvolvedora;

    private List<Desenvolvedora> desenvolvedoras;

    private Long selectedId;

    @Override
    public String execute() throws Exception {
        this.desenvolvedoras = this.desenvolvedoraService.buscarTodos();
        return "jsp/admin/desenvolvedoras/list";
    }

    public String create() throws Exception {
        this.formTitle = "Criação";
        return "jsp/admin/desenvolvedoras/form";
    }

    public String update() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperar(this.selectedId);
        this.formTitle = "Edição";
        return "jsp/admin/desenvolvedoras/form";
    }

    public String view() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperarComListas(this.selectedId);
        return "jsp/admin/desenvolvedoras/view";
    }

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

    public Desenvolvedora getDesenvolvedora() {
        return this.desenvolvedora;
    }

    public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public void setDesenvolvedoras(List<Desenvolvedora> desenvolvedoras) {
        this.desenvolvedoras = desenvolvedoras;
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
