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


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.touchtec.dali.options.OptionsProvider;
import br.com.touchtec.dali.target.Target;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.PlataformaService;


/**
 * Options Provider de plataforma.
 * 
 * @author filipe
 */
@Component
public class PlataformaProvider implements OptionsProvider<Plataforma> {

    @Autowired
    private PlataformaService plataformaService;

    @Override
    public Collection<Plataforma> getOptions(String view, Target target) {
        List<Plataforma> plataformas = this.plataformaService.listarTodos();
        Collections.sort(plataformas, new PlataformaComparator());
        return plataformas;
    }

    /**
     * Ordena as plataformas pelo nome usando a ordem natural.
     * 
     * @author filipe
     */
    public class PlataformaComparator implements Comparator<Plataforma> {

        @Override
        public int compare(Plataforma o1, Plataforma o2) {
            return o1.getNome().compareTo(o2.getNome());
        }

    }

}
