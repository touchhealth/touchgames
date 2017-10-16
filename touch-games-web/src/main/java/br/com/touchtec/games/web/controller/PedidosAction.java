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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.service.PedidoService;
import br.com.touchtec.twf.core.TWFActionSupport;


@Component
@Scope("request")
public class PedidosAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PedidoService pedidoService;

    private Pedido pedido;

    private List<Pedido> pedidos;

    private Long selectedId;

    @Override
    public String execute() throws Exception {
        this.pedidos = this.pedidoService.listarTodos();
        return "jsp/admin/pedidos/list";
    }

    public String view() throws Exception {
        this.pedido = this.pedidoService.recuperarComListas(this.selectedId);
        return "jsp/admin/pedidos/view";
    }

    public String remove() throws Exception {
        this.pedido = this.pedidoService.recuperar(this.selectedId);
        this.pedidoService.remover(this.pedido);
        this.addSuccessMessage(this.pedido + " removido com sucesso");
        return this.execute();
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getSelectedId() {
        return this.selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

}
