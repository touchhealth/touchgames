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

import br.com.touchtec.message.Named;

// EXERCICIO
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

    private List<Imagem> imagens = new ArrayList<>();

    // EXERCICIO
    public Float getPrecoComDesconto() {
        if (this.preco == null) {
            return 0f;
        }

        Float precoComDesconto = this.preco - (((float) this.desconto) / 100) * this.preco;
        if (precoComDesconto < 0) {
            return 0f;
        }

        precoComDesconto = (float) (Math.floor(precoComDesconto * 100) / 100);
        return precoComDesconto;
    }

    // EXERCICIO
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

    // EXERCICIO
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

    // EXERCICIO
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
     * [COMPOSIÇAO]
     *
     * Vamos usar EAGER mesmo, pois vamos buscar as imagens sempre e isso vai facilitar nossa vida. Mas cuidado com essa
     * estratégia.
     */
    // EXERCICIO
    public List<Imagem> getImagens() {
        return this.imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
