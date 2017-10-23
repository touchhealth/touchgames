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

package br.com.touchtec.games.core.crud;


import static br.com.touchtec.dali.crud.api.CrudViews.LIST;
import static br.com.touchtec.dali.crud.api.CrudViews.VIEW;
import static br.com.touchtec.dali.template.DaliTemplates.NUMBER_OUTPUT;

import java.util.Date;
import java.util.List;

import br.com.touchtec.dali.crud.api.AssociationDTO;
import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.command.CreateCommand;
import br.com.touchtec.dali.crud.command.ExcludeCommands;
import br.com.touchtec.dali.crud.command.UpdateCommand;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.crud.search.CustomClauseBuilder;
import br.com.touchtec.dali.template.Template;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.dali.view.Views;
import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.message.Named;


@Views({
        @View(config = "id; data"),
        @View(ids = LIST, config = "id; data; valorTotal"),
        @View(ids = VIEW, config = "id; data; itens; valorTotal")
})
@CrudMapping(entity = Pedido.class)
// EXERCICIO
@Named(key = "Pedido")
public class PedidoDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date data;

    private List<AssociationDTO<Long>> itens;

    private Float valorTotal;

    @Template(value = NUMBER_OUTPUT, params = "format = #0.00")
    public Float getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    // EXERCICIO
    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<AssociationDTO<Long>> getItens() {
        return this.itens;
    }

    public void setItens(List<AssociationDTO<Long>> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido #" + this.id + " : " + this.data;
    }

}
