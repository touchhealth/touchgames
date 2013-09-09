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

package br.com.touchtec.games.web.controller;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.touchtec.games.core.model.ItemPedido;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.web.CarrinhoDeCompras;
import br.com.touchtec.twf.core.TWFActionSupport;

import com.google.common.collect.Lists;

/**
 * 
 * 
 * @author filipe
 * 
 */
@Controller
@Scope(SCOPE_REQUEST)
public class ComprasAction extends TWFActionSupport {

	private static final long serialVersionUID = 4629730301565870620L;

	@Autowired
	private CarrinhoDeCompras carrinhoDeCompras;

	@Override
	public String execute() throws Exception {

		this.carrinhoDeCompras.setItems(this.getItens());

		return "jsp/carrinho_de_compras";
	}

	/**
	 * @return carrinhoDeCompras
	 */
	public CarrinhoDeCompras getCarrinhoDeCompras() {
		return this.carrinhoDeCompras;
	}

	/**
	 * @param carrinhoDeCompras
	 */
	public void setCarrinhoDeCompras(CarrinhoDeCompras carrinhoDeCompras) {
		this.carrinhoDeCompras = carrinhoDeCompras;
	}

	public List<Integer> getQuantidades() {
		return Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15);
	}

	private Set<ItemPedido> getItens() {

		Set<ItemPedido> items = new LinkedHashSet<ItemPedido>();

		for (Jogo jogo : this.getJogos()) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(1);
			itemPedido.setJogo(jogo);
			items.add(itemPedido);
		}

		return items;

	}

	private List<Jogo> getJogos() {

		List<Jogo> jogos = Lists.newArrayList();
		Jogo assassins = new Jogo();
		assassins.setNome("Assassins Creed");
		assassins.setDescricao("Jogo Bão");
		assassins.setPreco(2.68f);

		Jogo mario = new Jogo();
		mario.setNome("Mario");
		mario.setDescricao("Mario, um jogo cheio de diversão");
		mario.setPreco(10.00f);

		jogos.add(assassins);
		jogos.add(mario);

		return jogos;

	}

}
