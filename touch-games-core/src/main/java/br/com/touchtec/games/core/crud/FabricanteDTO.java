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


import static br.com.touchtec.dali.crud.api.CrudViews.SEARCH;
import static br.com.touchtec.dali.crud.operation.OperationProcessor.DefaultOperations.SAVE_NEW_OPERATION;
import static br.com.touchtec.dali.crud.operation.OperationProcessor.DefaultOperations.SAVE_OPERATION;
import static br.com.touchtec.dali.crud.operation.Position.AFTER;

import java.util.List;

import br.com.touchtec.dali.crud.api.AssociationDTO;
import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.crud.operation.DTOToEntityHandler;
import br.com.touchtec.dali.crud.operation.Handler;
import br.com.touchtec.dali.crud.operation.Operation;
import br.com.touchtec.dali.crud.operation.Operations;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.dali.view.Views;
import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.message.Named;


@Views({
        @View(ids = SEARCH, config = "nome"),
        @View(config = "nome; plataformas")
})
@CrudMapping(entity = Fabricante.class)
// Ver br.com.touchtec.dali.crud.config.DefaultCrudMapper#mapOperations
@Operations({
        @Operation(id = SAVE_NEW_OPERATION, handlers = @Handler(type = AssociaFabricanteAPlataformaHandler.class, position = AFTER, targetHandler = DTOToEntityHandler.class)),
        @Operation(id = SAVE_OPERATION, handlers = @Handler(type = AssociaFabricanteAPlataformaHandler.class, position = AFTER, targetHandler = DTOToEntityHandler.class))
})
@Named(key = "Fabricante")
public class FabricanteDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private List<AssociationDTO<Long>> plataformas;

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

    public List<AssociationDTO<Long>> getPlataformas() {
        return this.plataformas;
    }

    public void setPlataformas(List<AssociationDTO<Long>> plataformas) {
        this.plataformas = plataformas;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
