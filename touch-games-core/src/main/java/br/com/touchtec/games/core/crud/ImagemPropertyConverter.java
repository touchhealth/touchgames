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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import br.com.touchtec.dali.crud.converter.PropertyConverter;
import br.com.touchtec.dali.crud.manager.CrudManager;
import br.com.touchtec.dali.target.Target;
import br.com.touchtec.games.core.model.Imagem;
import br.com.touchtec.games.core.model.Jogo;


/**
 * @author filipe
 */
public class ImagemPropertyConverter implements PropertyConverter<JogoDTO, Jogo> {

    @Override
    public void setToDTO(Target arg0, Jogo entity, JogoDTO dto, CrudManager arg3) {
        // TODO
    }

    @Override
    public void setToEntity(Target arg0, Jogo entity, JogoDTO dto, CrudManager arg3) {
        try {
            for (File file : dto.getImagens()) {
                FileInputStream in = new FileInputStream(file);
                entity.getImagens().add(new Imagem(IOUtils.toByteArray(in)));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
