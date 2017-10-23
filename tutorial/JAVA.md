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

> #### Abra `EntidadeRaiz` e sobrescreva `equals()` e `hashCode()` usando o gerador do **Eclipse**.

## Serializable

Com um objeto seriável é possivel guardar o seu estado e recuperar de um byte stream.  
Com isso, podemos salvar um objeto num arquivo e recuperá-lo com o mesmo estado que foi serializado.

```java
public class EntidadeRaiz implements Serializable {
    // número de versao: se vc mudar seu objeto, mude esse numero 
    // Assim o Java sabe que houve uma alteração
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

Usamos para mostrar um descrição amigável de um objeto.  

> #### Abra a classe `Desenvolvedora` e sobrescreva `toString()` devolvendo **nome**.

## Java Beans

Java Beans são classes de dados. Possuem propriedades e **getters** e **setters**.  
Não possuem métodos de negócio.  
Vários frameworks assumem que estamos usando Java Beans. 


```java
public cass JavaBean {
    private String teste;

    public String getTeste(){
        return this.teste;
    }
    public void setTeste(String teste){
        this.teste = teste;
    }
}
```


[Next](JPA.md)

[Índice](index.md)
