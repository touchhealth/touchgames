$(document).observe("dom:loaded", function () {
    var contentNode = $('jogos-relacionados').down('.content');
    var genero = $('jogos-relacionados').dataset.genero;

    var atualizaJogosRecomendados = function () {
        contentNode.addClassName("loading");
        contentNode.update("");

        new Ajax.Request("Compras!jogosRecomendados.action?generoSelecionado=" + genero, {
            onSuccess: function (transport) {
                var jogos = transport.responseJSON.twfOriginalJSON;
                renderizaResposta(jogos);
                contentNode.removeClassName("loading");
            }
        });
    };

    /*
     Vamos criar programaticamente códgio HTML.
     Não é comum fazermos assim na prática. Dá muito trabalho. Mas vale saber como faz.
     */
    var renderizaResposta = function (jogos) {
        $(jogos).each(function (jogo) {
            var id = jogo.id;
            var nome = jogo.nome;
            var imagemId = jogo.imagemId || '';

            var anchorNode = new Element('a', {href: 'Compras!jogoDetalhes.action?jogoSelecionado.id=' + id});

            var boxNode = new Element('div', {class: 'jogo'});
            anchorNode.appendChild(boxNode);

            var imgNode = new Element('img', {
                src: "imagens?id=" + imagemId,
                onerror: "this.src='img/jogo_padrao.png'"
            });
            boxNode.appendChild(imgNode);

            var nomeNode = new Element('div', {class: 'nome'});
            nomeNode.update(nome);
            boxNode.appendChild(nomeNode);

            contentNode.appendChild(anchorNode);
        });
    };

    // chamada inicial, ao entrar na tela
    atualizaJogosRecomendados();

    // atualiza a cada 5s
    setInterval(atualizaJogosRecomendados, 5000);
});

