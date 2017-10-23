# Javascript

Javascript adiciona comportamento ao HTML
- HTML → estrutura
- CSS → estilos
- **Javascript → comportamento**

```js

// VAR
var message = "hello";

// FUNCTION

var hello = function(message) {
    console.log(message);
}

// ou

function hello(message) {
    console.log(message);
}

// IF
if(numero == 42) {

}

// FOR

for(var i = 0; i < array.length; i++){
    var element = array[i];
}

```

## Tudo é Mapa

**JSON:** JavaScript Objecy Notation

```js
var person = {};
person.firstName = “Maria”;
person.lastName = “Sharapova”;

var person = {
    firstName: 'Maria',
    lastName: 'Sharapova',
    firstGrandSlam: {
        name: 'Wimbledon',
        year: 2004
    },
    grandSlams: [2004, 2006, 2008, 2012],
    sayHello: function(message){
        return 'Hello ' + message;
    }
}
```

## Funções

É a parte que difere bastante do Java

```js
// Uma função pode receber outr afunção como argumento
// Lembrem das aulas de Compiladores
function  invokeAnotherFunction(f){
    f.call();
}

// Exemplo 1
var f = function(){
    console.log('eu sou uma função');
}
invokeAnotherFunction(f)

// Exemplo 2: função anônima
invokeAnotherFunction(function(){
    console.log('eu sou uma função');
})

```

> #### Abra o Chrome DEV Tools (F12) e cole no console

```js
var somaUm = function(n){
   return n + 1;
}

var dobra = function(n){
   return n*2;
}

var calcula = function(numero, operacao){
    var retorno = operacao(numero);
    console.log(retorno);
}
```

> #### Teste algumas chamadas

```js
calcula(3.14, dobra);
calcula(99, somaUm);
```

# PrototypeJS
- utilitários para manipular a DOM
- DOM?
- $('header') → document.getElementById('header')
- $$('seletor_css') → $$('#header .game')

[Documentaçao oficial](http://api.prototypejs.org/)

## Navegar pela DOM

```js
var divs = $('header').select('div'); // array
var jogo = $('jogos-lista').down('.jogo');
var div = $('jogos-lista').up('div');
```

## Iterando

```js

// Chamamos isso programação funcional

$$('.jogo').each(function(jogo){
    console.log(jogo)
});

var numeros = [2, 3, 4];
numeros.each(function(n){
    console.log(n)
});
```

## Eventos


```js
onclick, onfocus, onmouseover


element.observe('click', callback);

$('header').observe('click', function(e){
   console.log(e);
});

```

[Lista de eventos](https://www.w3schools.com/jsref/dom_obj_event.asp)

## O Efeito Bubble

```html
<div id='header'>
    <input  id='nome' name='jogo.nome'/>
</div>

$('nome').observe('click', function(){ … })

$('header').observe('click', function(){ … })

```

Nem todos os eventos borbulham: [onFocus](https://www.w3schools.com/jsref/event_onfocus.asp) (ver Technical Details)


## AJAX

O nome técnico é **requisições XHR**: _XMLHttpRequest_.   
Foi criado pela Microsoft para transportar XML como resposta da request.   
Evoluiu e hoje pode ser usado com outros tipos: XML, JSON, Texto ou mesmo HTML.


### Do lado do cliente

```js
new Ajax.Request('Compras!jogosRecomendados.action?genero=42',    {
      onSuccess : function(transport) {
        // twfOriginalJSON e uma peculiaridade do TWF
        var jogos = transport.responseJSON.twfOriginalJSON;

      },
      onFailure: function(transport){ … }
      ...
});
```

### Do lado da action

```java
import br.com.touchtec.json.JSONArray;
import br.com.touchtec.json.JSONObject;

// array de objetos
public JSONArray jogosRecomendados() {…}

// um objeto
public JSONObject jogo() {
    JSONObject json = new JSONObject();
    json.put("id", jogo.getId());
    json.put("nome", jogo.getNome());
    return json;
}

```

# Tarefa: mostrar jogos recomendados

> #### Abra `jogo_detalhes.jsp` e adicione no fim da página

```
    <script src="${app}/js/prototype.js" type="text/javascript" ></script>
    <script src="${app}/js/jogos_recomendados.js" type="text/javascript" ></script>
</g:screen>
```

> #### Abra `jogos_recomendados.js` e adicione

Vamos escrever nosso código dentro da função abaixo para que ela só seja executada quando a página estiver sido carregada.
Adicione dentro desta função todos os trechos a seguir um abaixo do outro.
```js
$(document).observe("dom:loaded", function(){

     // nosso código vem aqui

}
```

> #### Primeiro, selecionamos o alvo

```js
var contentNode = $('jogos-relacionados').down('.content');
var genero = $('jogos-relacionados').dataset.genero;
```

> #### Em seguida, adicione a função que fará a request

```js
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
```

> #### Adicione a função que renderiza a resposta

```js
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
```

O código acima vai criar uma lista de HTMLs com esta estrtura:

```html
<a href="Compras!jogoDetalhes.action?jogoSelecionado.id=351">
    <div class="jogo">
        <img src="img/jogo_padrao.png" onerror="this.src='img/jogo_padrao.png'">
        <div class="nome">
           Mario 2
        </div>
    </div>
</a>
```

> #### Por fim, invoque a função de atualização

```js
// chamada inicial, ao entrar na tela
atualizaJogosRecomendados();

// atualiza a cada 5s
setInterval(atualizaJogosRecomendados, 5000);
```

> #### Suba a aplicação (ou Recargue a página se já estiver de pé)
> Acesse a tela [/Compras.action]() e selecione um Jogo para ver a tela de **Detalhes**.  
> Repare na área abaixo do texto **"Você também pode gostar:"**
