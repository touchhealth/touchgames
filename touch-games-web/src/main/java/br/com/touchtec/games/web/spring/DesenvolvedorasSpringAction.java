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

package br.com.touchtec.games.web.spring;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.twf.core.TWFActionSupport;


@Component
@Scope("request")
public class DesenvolvedorasSpringAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DesenvolvedoraService desenvolvedoraService;

    private String method;

    private Desenvolvedora desenvolvedora;

    private List<Desenvolvedora> desenvolvedoras;

    private Long id;

    // ACOES -----------------------------------------------------------------------

    @Override
    public String execute() throws Exception {
        this.desenvolvedoras = this.desenvolvedoraService.listarTodos();
        return "jsp/struts/Desenvolvedoras";
    }

    public String create() throws Exception {
        this.method = "create";
        return this.execute();
    }

    public String update() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperar(this.id);
        this.method = "update";
        return this.execute();
    }

    public String save() throws Exception {
        if (this.desenvolvedora.getId() != null) {
            this.desenvolvedoraService.editar(this.desenvolvedora);
        } else {
            this.desenvolvedoraService.criar(this.desenvolvedora);
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.desenvolvedora = this.desenvolvedoraService.recuperar(this.id);
        this.desenvolvedoraService.remover(this.desenvolvedora);
        return this.execute();
    }

    // GETTERS e SETTERS ------------------------------------------------------------

    public String getMethod() {
        return this.method;
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

    public List<Desenvolvedora> getDesenvolvedoras() {
        return this.desenvolvedoras;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
