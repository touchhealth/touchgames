/**
 * - Eventos
 * - Funções como parametros
 * - seletor de id
 * - Ajax
 * - JSON
 * - setInterval
 * - each
 * - down, up
 */

$(document).observe("dom:loaded", function(){
	var contentNode = $('jogos-relacionados').down('.content');

	setInterval(function(){
		contentNode.addClassName("loading");
		contentNode.update("");
		
		new Ajax.Request("Compras!jogosRecomendados.action?generoSelecionado=RPG", {
			onSuccess : function(transport) {
				var jogos = transport.responseJSON.twfOriginalJSON;
				criarElementos(jogos);
			},
		});
	}, 5000);
	
	var criarElementos = function(jogos){
		$(jogos).each(function(jogo){
			var id = jogo.id;
			var nome = jogo.nome;
			var imagemId = jogo.imagemId || '';
			
			var anchorNode = new Element('a', { href: 'Compras!jogoDetalhes.action?jogoSelecionado.id=' + id});
			
			var boxNode = new Element('div', { class: 'jogo'});
			anchorNode.appendChild(boxNode);
			
			var imagemNode = new Element('img', { src: "imagens?id=" + imagemId});
			boxNode.appendChild(imagemNode);
			
			var nomeNode = new Element('div', { class: 'nome'});
			nomeNode.update(nome);
			boxNode.appendChild(nomeNode);
			
			contentNode.appendChild(anchorNode);
			
			contentNode.removeClassName("loading");
		});
	};
});

