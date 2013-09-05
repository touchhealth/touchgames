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

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * 
 * 
 * @author filipe
 * @since
 * 
 */
@MappedSuperclass
public abstract class EntidadeRaiz implements Serializable {

	private Long id;

	/**
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.print();
	}

	/**
	 * FIXME
	 * 
	 * @return
	 */
	protected abstract String print();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		EntidadeRaiz other = (EntidadeRaiz) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
