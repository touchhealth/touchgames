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


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
        if ((entity != null) && (entity.getImagens() != null) && entity.getImagens().iterator().hasNext()) {
            Imagem imagem = entity.getImagens().iterator().next();
            dto.setImagemId(imagem.getId());
        }
    }

    @Override
    public void setToEntity(Target arg0, Jogo entity, JogoDTO dto, CrudManager arg3) {

        try {
            FileInputStream in = null;
            if (arg0.getSimpleName().equals("imagem1")) {
                if (dto.getImagem1() != null) {
                    in = new FileInputStream(dto.getImagem1());
                }
            } else if (arg0.getSimpleName().equals("imagem2")) {
                if (dto.getImagem2() != null) {
                    in = new FileInputStream(dto.getImagem2());
                }
            } else if (arg0.getSimpleName().equals("imagem3")) {
                if (dto.getImagem3() != null) {
                    in = new FileInputStream(dto.getImagem3());
                }
            } else if (arg0.getSimpleName().equals("imagem4")) {
                if (dto.getImagem4() != null) {
                    in = new FileInputStream(dto.getImagem4());
                }
            } else if (arg0.getSimpleName().equals("imagem5")) {
                if (dto.getImagem5() != null) {
                    in = new FileInputStream(dto.getImagem5());
                }
            }
            if (in != null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                IOUtils.copy(in, out);
                entity.getImagens().add(new Imagem(out.toByteArray()));
            } else {
                entity.setImagens(new ArrayList<Imagem>());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
