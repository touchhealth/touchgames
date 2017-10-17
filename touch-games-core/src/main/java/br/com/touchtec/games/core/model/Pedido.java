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

package br.com.touchtec.games.core.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;


@Entity
public class Pedido extends EntidadeRaiz {

    private static final long serialVersionUID = 1L;

    private Date data;

    private List<ItemPedido> itens;

    /**
     * Este método somente funciona quando a lista de pedidos for inicializada.
     *
     * @return O valor total do pedido.
     */
    @Transient
    public Float getValorTotal() {
        Float valorTotal = 0f;
        List<ItemPedido> itens = this.getItens();

        if (CollectionUtils.isEmpty(itens)) {
            return valorTotal;
        }
        for (ItemPedido item : itens) {
            valorTotal += item.getValorTotalItem();
        }
        return valorTotal;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ItemPedido> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return String.format("Pedido #%s", this.getId());
    }
}
