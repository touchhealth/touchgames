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


import br.com.touchtec.dali.crud.api.AssociationDTO;
import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.crud.search.OrderBy;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.message.Named;


@View(config = "nome; fabricante")
@CrudMapping(entity = Plataforma.class)
@OrderBy("nome")
@Named(key = "Plataforma")
public class PlataformaDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private AssociationDTO<Long> fabricante;

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

    public AssociationDTO<Long> getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(AssociationDTO<Long> fabricante) {
        this.fabricante = fabricante;
    }

}
