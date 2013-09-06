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
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
public class ItemPedido extends EntidadeRaiz {

    private static final long serialVersionUID = -6159795502823858669L;

    private Jogo jogo;
    private int quantidade;

    /**
     * @return jogo
     */
    @ManyToOne
    public Jogo getJogo() {
        return this.jogo;
    }

    /**
     * @param jogo
     */
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    /**
     * @return quantidade
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    protected String print() {
        return String.format("%d %s", this.quantidade, this.jogo);
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
                .append(this.quantidade, other.quantidade) //
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
                .append(this.jogo) //
                .append(this.quantidade) //
                .toHashCode();
    }
}
