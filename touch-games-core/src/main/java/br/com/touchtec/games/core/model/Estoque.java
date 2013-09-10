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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
public class Estoque extends EntidadeRaiz {

    private static final long serialVersionUID = 1058715240939644326L;

    private int quantidade;
    private Plataforma plataforma;
    private Jogo jogo;

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

    /**
     * @return plataforma
     */
    @ManyToOne
    public Plataforma getPlataforma() {
        return this.plataforma;
    }

    /**
     * @param plataforma
     */
    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

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

    @Override
    protected String print() {
        return String.format("%d %s para %s", this.quantidade, this.jogo, this.plataforma);
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
        Estoque other = (Estoque) obj;
        return new EqualsBuilder() //
                .append(this.quantidade, other.quantidade) //
                .append(this.plataforma, other.plataforma) //
                .append(this.jogo, other.jogo) //
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
                .append(this.quantidade) //
                .append(this.plataforma) //
                .append(this.jogo) //
                .toHashCode();
    }

}
