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

package br.com.touchtec.games.web.struts;


import java.util.Arrays;
import java.util.List;

import br.com.touchtec.games.core.model.Desenvolvedora;
import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.service.DesenvolvedoraService;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.impl.DesenvolvedoraServiceImpl;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;
import br.com.touchtec.twf.core.TWFActionSupport;


public class JogosStrutsAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    private JogoService jogoService = new JogoServiceImpl();

    private DesenvolvedoraService desenvolvedoraService = new DesenvolvedoraServiceImpl();

    private String method;

    private Jogo jogo;

    private List<Jogo> jogos;

    private Long id;

    // ACOES -----------------------------------------------------------------------

    @Override
    public String execute() throws Exception {
        this.jogos = this.jogoService.listarTodos();
        return SUCCESS;
    }

    public String create() throws Exception {
        this.method = "create";
        return this.execute();
    }

    public String update() throws Exception {
        this.jogo = this.jogoService.recuperarComListas(this.id);
        this.method = "update";
        return this.execute();
    }

    public String save() throws Exception {
        if (this.jogo.getId() != null) {
            this.jogoService.editar(this.jogo);
        } else {
            this.jogoService.criar(this.jogo);
        }
        return this.execute();
    }

    public String remove() throws Exception {
        this.jogo = this.jogoService.recuperarComListas(this.id);
        this.jogoService.remover(this.jogo);
        return this.execute();
    }

    // GETTERS e SETTERS ------------------------------------------------------------

    public String getMethod() {
        return this.method;
    }

    public List<Genero> getGeneros() {
        return Arrays.asList(Genero.values());
    }

    public List<Desenvolvedora> getDesenvolvedoras() {
        return this.desenvolvedoraService.listarTodos();
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Jogo> getJogos() {
        return this.jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
