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


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.PlataformaService;

/**
 * @see "struts.xml"
 */
public class NavigationInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PlataformaService plataformaService;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();

        List<Plataforma> plataformas = this.plataformaService.listarTodos();
        request.setAttribute("todasPlataformas", plataformas);

        return invocation.invoke();
    }
}
