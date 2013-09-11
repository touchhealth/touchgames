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


import static com.google.common.base.Strings.nullToEmpty;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
public class Fabricante extends EntidadeRaiz {

    private static final long serialVersionUID = 1L;

    private String nome;

    private List<Plataforma> plataformas;

    /**
     * @return nome
     */
    @Column(unique = true)
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
     * @return plataformas
     */
    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL)
    public List<Plataforma> getPlataformas() {
        return this.plataformas;
    }

    /**
     * @param plataformas
     */
    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    @Override
    protected String print() {
        return nullToEmpty(this.nome);
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
        Fabricante other = (Fabricante) obj;
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
