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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.touchtec.games.core.service.PlataformaService;
import br.com.touchtec.message.Named;
import br.com.touchtec.spring.SpringBeanUtil;


/**
 * @author filipe
 * @author emesquita
 */
@Entity
@Table(name = "jogos", uniqueConstraints = { @UniqueConstraint(columnNames = "nome") })
@Named(key = "Jogo")
public class Jogo extends EntidadeRaiz {

    private static final long serialVersionUID = 242685942375730911L;

    private String nome;
    private String descricao;
    private List<Plataforma> plataformas;
    private List<Estoque> estoques;
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
     * @return descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return plataformas
     */
    @ManyToMany
    @OrderBy("nome")
    public List<Plataforma> getPlataformas() {
        return this.plataformas;
    }

    /**
     * @param plataformas
     */
    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    /**
     * @return estoques
     */
    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
    public List<Estoque> getEstoques() {
        return this.estoques;
    }

    /**
     * @param estoques
     */
    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    /**
     * @return genero
     */
    public Genero getGenero() {
        return this.genero;
    }

    /**
     * @param genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * @return desenvolvedora
     */
    @ManyToOne
    public Desenvolvedora getDesenvolvedora() {
        return this.desenvolvedora;
    }

    /**
     * @param desenvolvedora
     */
    public void setDesenvolvedora(Desenvolvedora desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    /**
     * @return preco
     */
    public Float getPreco() {
        return this.preco;
    }

    /**
     * @param preco
     */
    public void setPreco(Float preco) {
        this.preco = preco;
    }

    /**
     * @return desconto
     */
    public int getDesconto() {
        return this.desconto;
    }

    /**
     * @param desconto
     */
    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    /**
     * @return dataLancamento
     */
    public Date getDataLancamento() {
        return this.dataLancamento;
    }

    /**
     * @param dataLancamento
     */
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Vamos usar EAGER mesmo, pois vamos listar as imagens sempre e isso vai facilitar nossa vida. Mas cuidado com essa
     * estratégia.
     * 
     * @return imagens
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Imagem> getImagens() {
        return this.imagens;
    }

    /**
     * @param imagens
     */
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
