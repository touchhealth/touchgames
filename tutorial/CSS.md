# CSS

[Referência](http://www.w3schools.com/cssref/css_selectors.asp)

CSS adiciona estilos visuais ao HTML
- HTML → estrutura
- **CSS → estilos**
- Javascript → comportamento

```
#header {
	height: 90px;
	background: #005680 url('../img/bg-header.jpg');
}

/* comentário css */

- #header: seletor
- height, background:  propriedades
```

## Principais seletores

- **id:** #header { … }
- **class:** .game-box { … }
- **elemento:** div { … }, * { … }
- **descendente:** #center .game-box { … }
- **filho:** #center > .game-box { … }
- **atributo:** [value=30] { … }, [value]  { … }
- **OU:** #header, #footer { … }
- **E:** div.game-box { …}, #input[value=42] { … }

## Dimensões

- **width:** largura. 30px, 100%
- **height:** altura
- **margin**
- **border**
- **padding**

![](img/css2.png)

## Cores e Fontes

- **background:** cor, imagem de fundo
- **color:** cor do texto
- **font-size:** tamanho do texto

## Float

Faz um elemento _flutuar_
- **left:** flutua à esquerda
- **right:** flutua à direita
- **clear:** especifica que um elemento não pode flutuar sobre ele

> #### Acesse a página de exemplo: [/examples/float.html]()

## Display

Informa se um elemento deve ser **exibido** e **como**.

- **block:** inicia em um nova linha e ocupa 100% da largura
- **inline:** não quebra linha e ocupa apenas a alrgura necessária
- **none:** esconde um elemento
- **inline-block:** iguais aos inline, mas podem ter altura e largura

> #### Veja exemplos: [https://www.w3schools.com/css/css_display_visibility.asp]()

## Position

Posiciona elementos na tela

- **static:** estático em relação a _top_, _bottom_, _left_ e _right_. Padrão.
- **relative:** relativo à sua posição normal (static)
- **absolute:** relativo ao seu ancestral posicionado mais próximo
- **fixed:** relativo ao _viewport_ (área que o usuário esta vendo). Não muda de posição com o _scroll_.

> #### Veja exemplos: [https://www.w3schools.com/css/css_positioning.asp]()

## Pseudo classes

- **:hover:** quando o mouse passar sobre o elmento
- **:focus:** quando o foco estiver no elemento
- **:first-child:** quando o elemento estiver na primeira posiçao entre os filhos
- **:visited:** quando o link foi visitado (clicado)

```
.game:hover {
	text-decoration: underline;
}
```

# Adicionando estilos à nossa tela

> #### Adicione `games.css` no header de `WEB-INF/tags/screen.tag`

```html
<head>
    <title>Touch Games - Um toque de diversão</title>

    <link href="${app}/css/games.css" rel="stylesheet"/>
</head>
```

> #### Recarregue [/Compras.action]() (F5)
> Veja que a tela já está melhor, mas ainda precisamos corrigir algumas coisas

### Cabeçalho

> #### Em `games.css`, encontre a descomente regra `#header {}`
> Recarregue a tela

Repare que definimos para **#header**:
- background image
- height
- padding

> #### Descomente `.left {}` e `.right {}`
> Serão afetados o logo Touch Games a caixa de Busca à direita  
> Recarregue a tela

Repare que definimos a propriedade **float**

### Menu Plataformas

> #### Encontre e descomente a regra `#menu-plataformas li {}`
> Recarregue a tela  
> Precisamos melhorar

---

> #### Encontre e descomente e regra `.clearfix:after{}`
> Recarregue a tela  
> Repare no menu plataformas      

### Rodapé

Queremos que o rodapé fique preso no pé da página

> #### Encontre e descomente a regra `#footer {}`
> Recarregue a tela  
> Dê um scroll na tela. Repare que o rodapé não acompanha  

Faltou uma coisa:

> #### Encontre a regra `#screen {}` e descomente **position**
> Recarregue a tela  
> Dê um scroll na tela. Agora sim.  


[Next](JS.md)

[Índice](index.md)
