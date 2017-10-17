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


import static br.com.touchtec.dali.crud.operation.OperationProcessor.DefaultOperations.SAVE_NEW_OPERATION;
import static br.com.touchtec.dali.crud.operation.Position.AFTER;

import br.com.touchtec.dali.crud.api.CrudDTO;
import br.com.touchtec.dali.crud.config.CrudMapping;
import br.com.touchtec.dali.crud.operation.Handler;
import br.com.touchtec.dali.crud.operation.Operation;
import br.com.touchtec.dali.crud.operation.PersistEntityHandler;
import br.com.touchtec.dali.view.View;
import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.message.Named;


@View(config = "nome")
@CrudMapping(entity = Desenvolvedora.class)
// Consulte DefaultOperations#mapOperations()
@Operation(
        id = SAVE_NEW_OPERATION,
        handlers = @Handler(
                type = LogCriacaoRegistrosHandler.class,
                position = AFTER,
                targetHandler = PersistEntityHandler.class
        ))
@Named(key = "Desenvolvedora")
public class DesenvolvedoraDTO implements CrudDTO<Long> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
