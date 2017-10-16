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


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
public class ItemPedido extends EntidadeRaiz {

    private static final long serialVersionUID = 1L;

    private Jogo jogo;
    private int quantidade;
    private Plataforma plataforma;

    @Transient
    public Float getValorTotalItem() {
        return this.getQuantidade() * this.getJogo().getPrecoComDesconto();
    }

    @ManyToOne
    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @ManyToOne
    public Plataforma getPlataforma() {
        return this.plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    protected String print() {
        return String.format("%d x %s de %s", this.quantidade, this.jogo, this.plataforma);
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
        ItemPedido other = (ItemPedido) obj;
        return new EqualsBuilder() //
                .append(this.jogo, other.jogo) //
                .append(this.plataforma, other.plataforma) //
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
                .append(this.jogo) //
                .append(this.plataforma) //
                .toHashCode();
    }
}
