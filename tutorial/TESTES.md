# Testes Automáticos

São classes Java que executam **asserts** sobre outras classes Java.  
Essas classes são executadas automaticamente nos processos de `build` dos projetos.  
Aqui na Touch, usamos o **Jenkins**, uma ferramenta que gerencia a execução dos testes e nos informa quando eles falham. 

> #### Acesse a página do Jenkins do TWF
> [http://jenkins.touchtec.com.br:8080/job/twf/](http://jenkins.touchtec.com.br:8080/job/twf/)

# Por que testamos?
- Protegem-nos de possíveis bugs após refatorações
- Simulam um BUG reportado pelo cliente (teste de regressão)
- Auxiliam na modelagem da Arquitetura da aplicação (TDD)
- Divididos em 3 partes: Cenário, Teste em si, Asserts

# Como é um Teste?
Em Java, a ferramenta mais popular para testes é o **JUnit**.

```java
public class JogoTest {

    @Test
    public void precoComDescontoTest() {
        // CENARIO
        Jogo jogo = new Jogo();
        jogo.setPreco(100f);
        jogo.setDesconto(10);

        // TESTE
        Float precoComDesconto = jogo.getPrecoComDesconto();

        // ASSERTS
        Assert.assertEquals(90f, precoComDesconto);
    }
}
```

> #### Abra `JogoTest` no **Eclipse** (ou outra IDE) e execute

# Criando testes para JogoServiceImpl

> #### Abra `JogoServiceImplTest` e adicione o teste para criação

```java
    @Test
    public void criarTest() {
        // CENARIO
        Jogo jogo = this.criarJogo("Final Fantasy VIII");

        // TESTE
        Jogo jogoCriado = this.service.recuperar(jogo.getId());

        // ASSERTS
        jogoCriado = this.service.recuperar(jogoCriado.getId());
        // equals/hashcode É importante aqui
        Assert.assertEquals(jogo, jogoCriado);
    }

```

> #### Execute o teste

## Banco de Dados de Testes

É comum usarmos um banco de dados em memória para executar testes.  
Bancos em memória guardam dados na memória RAM (em vez de usar o File System) e por isso morrem após o teste.  

> #### Abra o arquivo `src/test/resources/META-INF/persistence.xml`
> Observe como banco é mapeado

---

> #### Seguindo o exemplo anterior, implemente `editarTest()` e `removerTest()`

---

> #### Adicione o teste para `buscarTodos()`

```java
    @Test
    public void buscarTodosTest() {
        // CENARIO
        Jogo chronoTrigger = this.criarJogo("Chrono Trigger");
        Jogo chronoCross = this.criarJogo("Chrono Cross");

        // TESTE
        List<Jogo> jogos = this.service.buscarTodos();

        // ASSERTS
        Assert.assertEquals(2, jogos.size());
        Assert.assertTrue(jogos.contains(chronoTrigger));
        Assert.assertTrue(jogos.contains(chronoCross));
    }
```

> #### Execute o teste
> Experimente rodar todos os testes (em vez de individualmente); observe que o `buscarTodosTest()` falha.  
> Precisamos limpar o banco a cada teste.

---

> #### Adicione o código abaixo (em qualquer lugar da classe) para limpar o banco **após** cada teste

```java
    @After
    public void limpaBanco() {
        // Funciona porque no persistence.xml setamos "hibernate.hbm2ddl.auto=create-drop"
        Persistence.createEntityManagerFactory("touch-games");
    }
```

> #### Execute novamente todos os testes simultaneamente 

---

> #### A partir do teste anterior, implemente `buscarPorNomeTest()`

[Next [Servlets]](SERVLETS.md)

[Índice](index.md)
