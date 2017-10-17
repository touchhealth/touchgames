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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.touchtec.games.core.model.Genero;
import br.com.touchtec.games.core.model.ItemPedido;
import br.com.touchtec.games.core.model.Jogo;
import br.com.touchtec.games.core.model.Pedido;
import br.com.touchtec.games.core.model.Plataforma;
import br.com.touchtec.games.core.service.JogoService;
import br.com.touchtec.games.core.service.PedidoService;
import br.com.touchtec.games.core.service.impl.JogoServiceImpl;
import br.com.touchtec.games.web.CarrinhoDeCompras;
import br.com.touchtec.json.JSONArray;
import br.com.touchtec.json.JSONObject;
import br.com.touchtec.twf.core.TWFActionSupport;


/**
 * @see "struts.xml"
 */
@Controller
@Scope("request")
public class ComprasAction extends TWFActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CarrinhoDeCompras carrinhoDeCompras;

    // Lembre-se, JogoService não foi implementado como um bean Spring.
    // Logo, não podemos injetá-lo com @Autowired
    private JogoService jogoService = new JogoServiceImpl();

    private List<Jogo> jogos;

    private Jogo jogoSelecionado;

    private Plataforma plataformaSelecionada;

    private Genero generoSelecionado;

    @Autowired
    private PedidoService pedidoService;

    private Pedido pedido;

    private String nomeDoJogo;

    @Override
    public String execute() throws Exception {
        this.jogos = this.jogoService.buscarTodos();
        return "jsp/jogos_lista";
    }

    public String jogoDetalhes() throws Exception {
        this.jogoSelecionado = this.jogoService.recuperarComListas(this.jogoSelecionado.getId());
        return "jsp/jogo_detalhes";
    }

    public String finalizarCompra() throws Exception {
        // se está vazio o carrinho apenas ignora
        if (CollectionUtils.isEmpty(this.carrinhoDeCompras.getItems())) {
            return "jsp/jogos_lista";
        }

        this.pedido = new Pedido();
        this.pedido.setItens(new ArrayList<ItemPedido>(this.carrinhoDeCompras.getItems()));
        this.pedido.setData(new Date());

        this.pedidoService.criar(this.pedido);

        this.carrinhoDeCompras.clear();

        return "jsp/sucesso";
    }

    public String jogosPorPlataforma() {
        this.jogos = this.jogoService.buscar(this.plataformaSelecionada);
        return "jsp/jogos_lista";
    }

    public JSONArray jogosRecomendados() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Não vamos fazer nada
        }

        this.jogos = this.jogoService.buscar(this.generoSelecionado);

        Collections.shuffle(this.jogos);

        JSONArray array = new JSONArray();

        for (Jogo jogo : this.jogos) {
            JSONObject json = new JSONObject();
            json.put("id", jogo.getId());
            json.put("nome", jogo.getNome());

            if (!jogo.getImagens().isEmpty()) {
                json.put("imagemId", jogo.getImagens().get(0).getId());
            }

            array.put(json);

            if (array.length() == 2) {
                break;
            }
        }

        return array;
    }

    public String buscarPorNome() {
        this.jogos = this.jogoService.buscar(this.nomeDoJogo);
        return "jsp/jogos_lista";
    }

    public List<Jogo> getJogos() {
        return this.jogos;
    }

    public Jogo getJogoSelecionado() {
        return this.jogoSelecionado;
    }

    public void setJogoSelecionado(Jogo jogoSelecionado) {
        this.jogoSelecionado = jogoSelecionado;
    }

    public Plataforma getPlataformaSelecionada() {
        return this.plataformaSelecionada;
    }

    public void setPlataformaSelecionada(Plataforma plataformaSelecionada) {
        this.plataformaSelecionada = plataformaSelecionada;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public String getNomeDoJogo() {
        return this.nomeDoJogo;
    }

    public void setNomeDoJogo(String nomeDoJogo) {
        this.nomeDoJogo = nomeDoJogo;
    }

    public Genero getGeneroSelecionado() {
        return this.generoSelecionado;
    }

    public void setGeneroSelecionado(Genero generoSelecionado) {
        this.generoSelecionado = generoSelecionado;
    }

    public CarrinhoDeCompras getCarrinhoDeCompras() {
        return this.carrinhoDeCompras;
    }

}
