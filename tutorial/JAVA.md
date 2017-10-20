# Java

## Equals/Hashcode

*Equals* faz a comparação entre objetos.

*Hashcode* Faz o calculo do hash do objeto.

Exemplos abaixo do equals e hashcode da EntidadeRaiz:

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

Com um objeto seriável, é possivel guardar o seu estado e recuperar de um byte stream.  
Com isso, por exemplo, podemos salvar um objeto num arquivo e recuperá-lo com o mesmo estado que foi serializado.

```java
public class EntidadeRaiz implements Serializable {
    private static final long serialVersionUID = 1L;
```

## Intefaces e classes abstratas

---
:books: Material de apoio

[Tutorial Interface do Java](https://docs.oracle.com/javase/tutorial/java/IandI/index.html)

---

## Enum

Enum é um tipo de dado em java que representa um conjunto de constantes.

```java
public enum Genero {
    ACAO, ESPORTE, RPG;
}
```

---
:books: Material de apoio

[Java Enum](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)

---

## toString()

O toString() vem do Object.  
Ele é usado para representarmos o objeto literalmente.  
A implementação default simplesmente concatena o nome da classe seguido do hashcode dela em hexadecimal (ex: Object@af21bc). A menos que voce seja um ciborgue, para um humano é dificil a interpretação disso. O que fazemos geralmente é sobrescrever esse metodo e implementar de algum modo que fique facil a identificação do objeto.

```java
@Override
public String toString() {
    return this.nome;
}
```

> #### Implemente os toString() na Desenvolvedora.

## Java Beans

Vamos usar esse padrão para expor as propriedades das classes atraves dos getters/setters.

```java
public cass JavaBean {
    private String teste;

    public String getTeste(){
        return this.teste;
    }
    public void setTeste(String value){
        this.teste = value;
    }
}
```
