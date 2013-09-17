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


import br.com.touchtec.dali.crud.converter.PropertyConverter;
import br.com.touchtec.dali.crud.manager.CrudManager;
import br.com.touchtec.dali.target.Target;
import br.com.touchtec.games.core.model.Pedido;


/**
 * Usado somente para setar o valor total do pedido usando o método já implementado na entidade para isso.
 * 
 * @author filipe
 */
public class PedidoPropertyConverter implements PropertyConverter<PedidoDTO, Pedido> {

    @Override
    public void setToDTO(Target target, Pedido entity, PedidoDTO dto, CrudManager manager) {
        dto.setValorTotal(entity.getValorTotal());
    }

    @Override
    public void setToEntity(Target target, Pedido entity, PedidoDTO dto, CrudManager manager) {
        // TODO Auto-generated method stub

    }

}
