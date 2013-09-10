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

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.touchtec.message.Named;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
@Named(key = "Desenvolvedora")
public class Desenvolvedora extends EntidadeRaiz {

    private static final long serialVersionUID = -3667462095435459034L;

    private List<Jogo> jogos;
    private String nome;

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
     * @return jogos
     */
    @ManyToMany(mappedBy = "desenvolvedora")
    public List<Jogo> getJogos() {
        return this.jogos;
    }

    /**
     * @param jogos
     */
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
