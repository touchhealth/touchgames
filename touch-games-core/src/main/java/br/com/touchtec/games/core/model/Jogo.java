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

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 
 * @author filipe
 * @since
 * 
 */
@Entity
@Table(name = "jogos")
public class Jogo extends EntidadeRaiz {

	private String nome;
	private String descricao;
	private List<Plataforma> plataformas;
	private List<Estoque> estoques;
	private Genero genero;
	private Desenvolvedora desenvolvedora;
	private Float preco;
	private int desconto;
	private Date dataLancamento;
	private List<Imagem> imagens;

	@Override
	protected String print() {
		return this.nome;
	}

}
