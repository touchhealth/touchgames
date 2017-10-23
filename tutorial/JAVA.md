# Java

Vamos abordar alguns conceitos importantes da linguagem que serão usados no treinamento.

## Equals/Hashcode

*Equals* faz a comparação entre objetos.  
*Hashcode* Faz o calculo do hash do objeto.

> #### Abra `EntidadeRaiz` e sobrescreva `equals()` e `hashCode()` usando o gerador do **Eclipse**.

```java
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (this.getClass() != obj.getClass()) {
        return false;
    }
    EntidadeRaiz other = (EntidadeRaiz) obj;
    if (this.id == null) {
        if (other.id != null) {
            return false;
        }
    } else if (!this.id.equals(other.id)) {
        return false;
    }
    return true;
}
```


## Serializable

Com um objeto seriável é possivel guardar o seu estado e recuperar de um byte stream.  
Com isso, podemos salvar um objeto num arquivo e recuperá-lo com o mesmo estado que foi serializado.

```java
public class EntidadeRaiz implements Serializable {
    // número de versao: se vc mudar seu objeto, mude esse numero 
    // Assim o Java sabe que houve uma alteração
    private static final long serialVersionUID = 1L;
```

## Super Classes e Interfaces

**Super Classes**: fornecem implementações de métodos que podem ser usados pelos filhos.  
**Interfaces**: definem um __contrato__ (assinatura de métodos) que **devem** ser implementados pelos filhos.

> #### Abra a entidade `Imagem` e faça-a sobrescrever `EntidadeRaiz`

```java
...
public class Imagem extends EntidadeRaiz {
   ...
```


> #### Abra `DesenvolvedoraServiceImpl` e implemente `DesenvolvedoraService`
> Descomente as anotações `@Override` dos métodos  
> Implementaremos os métodos nos próximos exercícios

```java
public class DesenvolvedoraServiceImpl implements DesenvolvedoraService {
    ...
```


---
:books: Material de apoio

[Tutorial Interface do Java](https://docs.oracle.com/javase/tutorial/java/IandI/index.html)

---

## Enum

Enum é um tipo de dado em java que representa um conjunto de constantes.

> #### Abra a classe `Genero` e adicione as opções abaixo 

```java
public enum Genero {
    ACAO, 
    
    ESPORTE, 
    
    RPG;
}
```

---
:books: Material de apoio

[Java Enum](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)

---

## toString()

Usamos para mostrar um descrição amigável de um objeto.  

> #### Abra a classe `Desenvolvedora` e sobrescreva `toString()` devolvendo **nome**

## Java Beans

Java Beans são classes de dados. Possuem propriedades e **getters** e **setters**.  
Não possuem métodos de negócio.  
Vários frameworks assumem que estamos usando Java Beans. 


```java
public class JavaBean {
    private String teste;

    public String getTeste(){
        return this.teste;
    }
    public void setTeste(String teste){
        this.teste = teste;
    }
}
```


[Next [JPA]](JPA.md)

[Índice](index.md)
