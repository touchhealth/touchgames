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


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.touchtec.message.Named;


@Entity
@Named(key = "Desenvolvedora")
public class Desenvolvedora extends EntidadeRaiz {

    private static final long serialVersionUID = 1L;

    private List<Jogo> jogos;

    private String nome;

    @Column(unique = true)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy = "desenvolvedora")
    @OrderBy("nome")
    public List<Jogo> getJogos() {
        return this.jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    @Override
    protected String print() {
        return this.nome;
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
        Desenvolvedora other = (Desenvolvedora) obj;
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
