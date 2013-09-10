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
import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.service.DesenvolvedoraService;


/**
 * @author emesquita
 */
@Component
@TypesToConvert(types = Desenvolvedora.class)
public class DesenvolvedoraConverter implements StringConverter<Desenvolvedora> {

    @Autowired
    private DesenvolvedoraService service;

    public String toString(Map<String, Object> context, Desenvolvedora desenvolvedora) {
        return desenvolvedora.getId().toString();
    }

    public Desenvolvedora fromString(Map<String, Object> context, String string, Class<? extends Desenvolvedora> clazz) {
        Long id = Long.parseLong(string);
        return this.service.recuperar(id);
    }

}
