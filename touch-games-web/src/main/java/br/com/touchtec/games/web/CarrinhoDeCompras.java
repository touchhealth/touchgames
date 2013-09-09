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

package br.com.touchtec.games.web;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.ItemPedido;

/**
 * @author bbviana
 */
@Scope(SCOPE_SESSION)
@Component
public class CarrinhoDeCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	// Não queremos repetições e queremos manter a ordem
	private Set<ItemPedido> items = new LinkedHashSet<ItemPedido>();

	/**
	 * @return items
	 */
	public Set<ItemPedido> getItems() {
		return this.items;
	}

	/**
	 * @param items
	 */
	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}

}
