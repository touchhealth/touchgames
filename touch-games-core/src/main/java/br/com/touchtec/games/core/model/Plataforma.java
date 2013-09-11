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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Plataforma extends EntidadeRaiz {

    private static final long serialVersionUID = -2400143269814546492L;

    private String nome;

    private Fabricante fabricante;

    @Override
    protected String print() {
        return this.nome;
    }

    /**
     * @return nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return fabricante
     */
    @ManyToOne
    // !!!!!!!!!!!!!!!!!!!!!!! mto importante para ex.
    // http://www.mkyong.com/hibernate/cascade-jpa-hibernate-annotation-common-mistake/
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Fabricante getFabricante() {
        return this.fabricante;
    }

    /**
     * @param fabricante
     */
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
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
        Plataforma other = (Plataforma) obj;
        return new EqualsBuilder() //
                .append(this.nome, other.nome) //
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
                .append(this.nome) //
                .toHashCode();
    }
}
