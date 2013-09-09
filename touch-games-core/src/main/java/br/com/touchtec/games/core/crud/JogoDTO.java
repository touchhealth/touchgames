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

package br.com.touchtec.games.core.crud;


import java.util.Date;
import java.util.List;

import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Imagem;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.message.Named;


/**
 * @author filipe
 */
@View(config = "nome, descricao, genero, preco, desconto, dataLancamento")
@CrudMapping(entity = Jogo.class)
@Named(key = "Jogo")
public class JogoDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 7302375495720376865L;

    private Long id;
    private String nome;
    private String descricao;
    private List<Plataforma> plataformas;
    private Genero genero;
    private Desenvolvedora desenvolvedora;
    private Float preco;
    private int desconto;
    private Date dataLancamento;
    private List<Imagem> imagens;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

}
