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


import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.touchtec.games.core.model.ItemPedido;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Plataforma;


/**
 * @author bbviana
 */
@Scope("session")
@Component
public class CarrinhoDeCompras implements Serializable {

    private static final long serialVersionUID = 1L;

    // Não queremos repetições e queremos manter a ordem
    private Set<ItemPedido> items = new LinkedHashSet<ItemPedido>();

    public void addItem(Jogo jogo, Integer quantidade, Plataforma plataforma) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setJogo(jogo);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPlataforma(plataforma);

        this.items.remove(itemPedido);

        this.items.add(itemPedido);
    }

    public void removeItem(Integer index) {
        // como garantimos a ordem do set, é possivel encontrar um item iterando

        int i = 0;
        for (Iterator iterator = this.items.iterator(); iterator.hasNext(); i++) {
            iterator.next();
            if (i == index) {
                iterator.remove();
                break;
            }
        }

    }

    public Set<ItemPedido> getItems() {
        return this.items;
    }

    public void clear() {
        this.getItems().clear();
    }

    public Float getTotal() {
        Float total = 0f;
        for (ItemPedido item : this.items) {
            total += item.getJogo().getPrecoComDesconto() * item.getQuantidade();
        }
        return total;
    }
}
