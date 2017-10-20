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


// EXERCICIO
public abstract class EntidadeRaiz implements Serializable {

    // número de versao: se vc mudar seu objeto, mude esse numero
    // Assim o Java sabe que houve uma alteração
    private static final long serialVersionUID = 1L;

    private Long id;

    // EXERCICIO
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Regra: sempre sobrescreva o hashCode() quando sobrescrever o equals().
     * Lembrem das aulas sobre Tabela de Hash; elas eram úteis.
     */

    // EXERCICIO: Implemente equals() e hashcode()

}
