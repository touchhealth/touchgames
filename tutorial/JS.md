# Javascript

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

> #### Abra o Chrome DEV Tools e cole no console

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



