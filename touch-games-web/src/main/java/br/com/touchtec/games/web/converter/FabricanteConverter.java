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

package br.com.touchtec.games.web.converter;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.touchtec.conversion.StringConverter;
import br.com.touchtec.conversion.TypesToConvert;
import br.com.touchtec.games.core.model.Fabricante;
import br.com.touchtec.games.core.service.FabricanteService;


/**
 * @author emesquita
 */
@Component
@TypesToConvert(types = Fabricante.class)
public class FabricanteConverter implements StringConverter<Fabricante> {

    @Autowired
    private FabricanteService service;

    public String toString(Map<String, Object> context, Fabricante fabricante) {
        return fabricante.getId().toString();
    }

    public Fabricante fromString(Map<String, Object> context, String string, Class<? extends Fabricante> clazz) {
        Long id = Long.parseLong(string);
        return this.service.recuperar(id);
    }

}
