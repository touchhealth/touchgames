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


import static br.com.touchtec.dali.crud.api.CrudViews.CREATE;
import static br.com.touchtec.dali.crud.api.CrudViews.LIST;
import static br.com.touchtec.dali.crud.api.CrudViews.SEARCH;
import static br.com.touchtec.dali.crud.api.CrudViews.UPDATE;
import static br.com.touchtec.dali.crud.api.CrudViews.VIEW;
import static br.com.touchtec.dali.template.DaliTemplates.NUMBER_INPUT;
import static br.com.touchtec.dali.template.DaliTemplates.NUMBER_OUTPUT;
import static br.com.touchtec.dali.template.DaliTemplates.RADIO_SELECT;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.touchtec.dali.crud.api.AssociationDTO;
import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.crud.converter.CustomPropertyConverter;
import br.com.touchtec.dali.crud.search.SearchClause;
import br.com.touchtec.dali.template.Template;
import br.com.touchtec.dali.template.Templates;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.dali.view.Views;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.message.Named;


@Views({
        @View(ids = SEARCH, config = "nome; descricao; genero; dataLancamento"),
        @View(ids = {CREATE, UPDATE}, config = "{geral[nome; descricao; genero;desenvolvedora;plataformas;dataLancamento],preco[preco; desconto],imagens[imagens]}"),
        @View(config = "nome; descricao; genero;dataLancamento; plataformas; desenvolvedora;preco; desconto")
})
@CrudMapping(entity = Jogo.class)
@Named(key = "Jogo")
public class JogoDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String descricao;

    private List<AssociationDTO<Long>> plataformas;

    private Genero genero;

    private AssociationDTO<Long> desenvolvedora;

    private Float preco;

    private Integer desconto;

    private Date dataLancamento;

    private List<File> imagens = new ArrayList<File>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    @Template(views = {SEARCH, CREATE}, value = RADIO_SELECT)
    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Templates({
            @Template(views = {CREATE, UPDATE}, value = NUMBER_INPUT, params = "format = #.00"),
            @Template(views = {VIEW, LIST}, value = NUMBER_OUTPUT, params = "format = \u00A4 #.00"),
    })
    public Float getPreco() {
        return this.preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getDesconto() {
        return this.desconto;
    }

    public void setDesconto(Integer desconto) {
        this.desconto = desconto;
    }

    @SearchClause("dataLancamento >= :dataLancamento")
    public Date getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public List<AssociationDTO<Long>> getPlataformas() {
        return this.plataformas;
    }

    public void setPlataformas(List<AssociationDTO<Long>> plataformas) {
        this.plataformas = plataformas;
    }

    public AssociationDTO<Long> getDesenvolvedora() {
        return this.desenvolvedora;
    }

    public void setDesenvolvedora(AssociationDTO<Long> desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    @CustomPropertyConverter(ImagemPropertyConverter.class)
    public List<File> getImagens() {
        return this.imagens;
    }

    public void setImagens(List<File> imagens) {
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
