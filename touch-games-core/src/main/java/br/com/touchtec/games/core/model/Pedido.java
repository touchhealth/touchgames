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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
public class Pedido extends EntidadeRaiz {

    private static final long serialVersionUID = -9113857980448358719L;

    private Date data;
    private List<ItemPedido> itens;

    /**
     * @return data
     */
    public Date getData() {
        return this.data;
    }

    /**
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return itens
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<ItemPedido> getItens() {
        return this.itens;
    }

    /**
     * @param itens
     */
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    protected String print() {
        return String.format("%s", this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Pedido other = (Pedido) obj;
        return new EqualsBuilder() //
                .append(this.data, other.data) //
                .append(this.itens == null ? null : new ArrayList<ItemPedido>(this.itens), //
                        other.itens == null ? null : new ArrayList<ItemPedido>(this.itens)) //
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
                .append(this.data) //
                .append(this.itens) //
                .toHashCode();
    }
}
