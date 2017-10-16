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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.touchtec.message.Named;


@Entity
@Table(name = "jogos")
@Named(key = "Jogo")
public class Jogo extends EntidadeRaiz {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String descricao;

    private List<Plataforma> plataformas;

    private Genero genero;

    private Desenvolvedora desenvolvedora;

    private Float preco;

    private int desconto;

    private Date dataLancamento;

    private List<Imagem> imagens = new ArrayList<Imagem>();

    /**
     * @return O preco com desconto.
     */
    @Transient
    public Float getPrecoComDesconto() {
        if (this.preco == null) {
            return 0f;
        }

        Float precoComDesconto = this.preco - ((((float) this.desconto) / 100) * this.preco);
        if (precoComDesconto < 0) {
            return 0f;
        }

        precoComDesconto = (float) (Math.floor(precoComDesconto * 100) / 100);
        return precoComDesconto;
    }

    @Column(unique = true)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @ManyToMany
    @OrderBy("nome")
    public List<Plataforma> getPlataformas() {
        return this.plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @ManyToOne
    public Desenvolvedora getDesenvolvedora() {
        return this.desenvolvedora;
    }

    public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Float getPreco() {
        return this.preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getDesconto() {
        return this.desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public Date getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Vamos usar EAGER mesmo, pois vamos listar as imagens sempre e isso vai facilitar nossa vida. Mas cuidado com essa
     * estratégia.
     *
     * @return imagens
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public List<Imagem> getImagens() {
        return this.imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
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
        Jogo other = (Jogo) obj;
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
