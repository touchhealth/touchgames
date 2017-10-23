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


import javax.persistence.Entity;


@Entity
public class Imagem { // EXERCICIO

    private static final long serialVersionUID = 1L;

    private byte[] bytes;

    /**
     * Este construtor é necessário para o Hibernate e pode ser protected (Como o Hibernate acessa?)
     */
    @SuppressWarnings("unused")
    protected Imagem() {
        super();
    }

    public Imagem(byte[] bytes) {
        this.bytes = bytes;
    }

    // EXERCICIO
    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
