# Dali
Dali é um _framework_ interno da Touch usado para renderizar _Java Beans_.   
Veja o [Showcase](http://showcase.touchtec.com.br/dali-showcase).   

# CRUD

Um dos módulos do Dali é responsável por renderizar **telas de cadastro** (CRUD).
- Create
- Retrieve
- Update
- Delete

[Veja no Showcase](http://showcase.touchtec.com.br/dali-showcase/tutorial/crud.action)

## DTO e Entidade

Um CRUD DTO é um espelho da Entidade.  
Cada propriedade da Entidade que se deseje usar tem uma correspondência no DTO.  
Associações usam um tipo de objeto diferente: **AssociationDTO**


```java
@View(config = "nome; fabricante")
@CrudMapping(entity = Plataforma.class)
public class PlataformaDTO implements CrudDTO<Long> {
    private Long id;

    private String nome;

    private AssociationDTO<Long> fabricante;
} 
```

```java
@Entity
public class Plataforma {
    private Long id;

    private String nome;

    private Fabricante fabricante;

    // getters e setters
}
```

Um exemplo mais complexo

```java
@Views({
        @View(ids = SEARCH, config = "nome; descricao; genero; dataLancamento"),
        @View(ids = {CREATE, UPDATE}, config = "" +
                "{ geral [nome; descricao; genero; desenvolvedora; plataformas; dataLancamento]," +
                " preco [preco; desconto], " +
                " imagens[imagens] " +
                "}"),
        @View(config = "nome; descricao; genero; dataLancamento; plataformas; desenvolvedora; preco; desconto")
})
@CrudMapping(entity = Jogo.class)
@Named(key = "Jogo")
public class JogoDTO implements CrudDTO<Long> {

}
```

## @View

> #### Abra PlataformaDTO e adicione as anotações do CRUD

```java
@View(config = "nome; fabricante")
@CrudMapping(entity = Plataforma.class)
public class PlataformaDTO implements CrudDTO<Long> { }
```

## AssociationDTO

> #### Adicione o relacionamento para Fabricante
> Repare que usamos **AssociationDTO**

```java
private AssociationDTO<Long> fabricante;

public AssociationDTO<Long> getFabricante() {
    return this.fabricante;
}

public void setFabricante(AssociationDTO<Long> fabricante) {
    this.fabricante = fabricante;
}
```

> #### Reinicie e acesse
> [/crud/Plataforma.action]()

---

> #### Faça alguns cadastros

## @OrderBy

Repare que nossa busca não devolve os resultados ordenados alfabeticamente.  
Vamos mudar isso.

> #### Ordene Plataformas por nome

```java
@OrderBy("nome")
public class PlataformaDTO implements CrudDTO<Long> { }
```

> #### Reinicie e acesse
> [/crud/Plataforma.action]()  
> Observe que os resultados da lista voltam ordenados

## @ExcludeCommands

Na tela de Pedidos, não queremos permitir a Criação ou Edição de registros.  
Queremos usar a tela apenas para consulta e remoção.

> #### Acesse a tela de Pedidos
> [/crud/Pedido.action]()  
> Repare que todas as operações de CRUD estão disponíveis

---

> #### Abra `PedidoDTO`
> Remova as operações e Criação e Edição

```java
@ExcludeCommands({CreateCommand.class, UpdateCommand.class})
public class PedidoDTO implements CrudDTO<Long> { }
```

Nao reinicie ainda.

## @CustomClauseBuilder

Ainda na tela de Pedidos, faça uma busca por Datas. Por que não retornou nada?  
Porque as datas dos Pedidos estão salvas no formato completo: dd/MM/yyy HH:mm:ss e milissegundos.  
E a busca adiciona uma cláusula  `WHERE data = :data`, onde :data é o valor da tela com 00:00:00.000.  
Precisamos informar ao CRUD que queremos buscar por pedidos efetuados no **mesmo dia** da data informada na tela.

> #### Altere a cláusula de busca para usar um intervalo
> Abra `DataPedidoClauseBuilder` e preencha

```java
@Override
public void buildClause(Target target, CrudQueryBuilder builder, CrudManager manager) {
    Date dataAlvo = (Date) target.getValue();

    if (dataAlvo == null) {
        return;
    }

    builder.appendWhere("data >= :dataInicio AND data < :dataFim");

    // Use o import org.apache.commons.lang3.time.DateUtils
    builder.createParameter("dataInicio", DateUtils.truncate(dataAlvo, Calendar.DATE));
    builder.createParameter("dataFim", DateUtils.ceiling(dataAlvo, Calendar.DATE));
}
```

> #### Anote a propriedade **data** com o **DataPedidoClauseBuilder** criado
> Abra `PedidoDTO`

```java
@CustomClauseBuilder(DataPedidoClauseBuilder.class)
public Date getData() {
    return this.data;
}   
```

> #### Reinicie e acesse
> [/crud/Pedido.action]()  
> Confira a busca por data  
> Confira que os botões de Criar e Editar não aparecem na lista

## @Template

Vamos alterar alguns templates padrão do CRUD.

> #### Acesse o casdastro de Jogos
> [/crud/Jogo.action]()

---

> #### Modifique o campo **Gênero** para usar um **radio** em vez de **select**

```java
    @Template(views = INPUT, value = DaliTemplates.RADIO_SELECT)
    public Genero getGenero() {
        return this.genero;
    }
```

O campo de **preço** não está no formatado para mostrar 2 casas decimais. Façamos.

> #### Altere o formato nemérico do campo **preco**
> Repare que aqui não alteramos o template sm si; apenas passamos um parâmetro.

```java
    @Template(params = "format = #0.00")
    public Float getPreco() {
        return this.preco;
    }
```

> #### Reinicie e veja as alterações
> [/crud/Jogo.action]()

## @View mais complexo
As telas de Criação e Edição podem ficar mais organizadas.  
Vamos usar alguns recursos do **Dali** para mudar a forma como renderizamos essas telas.

> #### Adicione mais um @View à classe `JogoDTO` (dentro de @Views)

```java
        @View(ids = {CREATE, UPDATE}, config = "{ " +
                "geral [nome; descricao; genero; desenvolvedora; plataformas; dataLancamento]," +
                "preco [preco; desconto], " +
                "imagens [imagens] " +
        "}")
                
```

O que há de diferente?
- Operador `{}`, que agrupa campos usando abas
- Operador `[]`, que agrupa campos usando paineis

> #### Reinicie e veja as alterações nas telas de Criação e Edição
> [/crud/Jogo.action]()  
> [Mais detalhes no Showcase](http://showcase.touchtec.com.br/dali-showcase/sintaxe.action)

## @CustomPropertyConverter

Se você reparou na aba **Imagens** das telas de Criação e Edição, verá que está em branco.  
O CRUD não soube renderizá-la porque não soube converter o tipo `File` que está no DTO para o tipo `Imagem` que está na Entidade.  
Poderíamos ter usado `Imagem` no DTO também para a conversão ficar automática. Fizemos assim apenas para exercitar o conceito de `PropertyConverter`.  
Precismos ensinar ao CRUD como se converte `File` em `Imagem` e vice-versa.

> #### Abra a classe `ImagemPropertyConverter` e complete com o código abaixo

```java
    @Override
    public void setToDTO(Target target, Jogo entity, JogoDTO dto, CrudManager manager) {
        for (Imagem imagem : entity.getImagens()) {
            // Vamos usar o id da imagem como nome do arquivo
            File file = new File(imagem.getId().toString());
            dto.getImagens().add(file);
        }
    }

    @Override
    public void setToEntity(Target target, Jogo entity, JogoDTO dto, CrudManager manager) {
        try {
            for (File file : dto.getImagens()) {
                FileInputStream in = new FileInputStream(file);
                Imagem imagem = new Imagem(IOUtils.toByteArray(in));
                entity.getImagens().add(imagem);
            }
        } catch (IOException e) {
            throw new ConversionException(e);
        }
    }
```

> #### Marque a propriedade **imagens** para usar o conversor acima

```java
    @CustomPropertyConverter(ImagemPropertyConverter.class)
    public List<File> getImagens() {
        return this.imagens;
    }
```

> #### Reinicie e acesse a tela de Edição de algum Jogo que tenha imagens
> [/crud/Jogo.action]()  
> Repare que apenas os ids das imagens são exibidas. Precisamos corrigir isso.

## dali-app-resources
O Dali não tem por padrão um template para mostrar `File`. Vamos criar um.  
Existem **duas formas** para alterar o template de uma propriedade:

- Uma nós já vimos: `@Template`
- Outra é criando um arquivo (do tipo `Freemarker`) na pasta `dali-app-resources`

### Freemarker
Freemarker é um _Template Engine_ usado para renderizar **principalmente** código HTML.  
Não renderiza exclusivamente HTML. Pode renderizar qualquer coisa. Até Java.  
É muito parecido com JSP. Então por que não usar JSP? JSPs exigem um servidor (Tomcat) para serem renderizados; pois são convertidos para **Servlets**. Um template Freemarker não. Isso nos dá mais flexibilidade.

[Freemarker: página oficial](http://freemarker.org/)

> #### Crie um arquivo com o nome `imagens.ftl` na pasta `webapp/dali-app-resources/jogo/`
> O Dali vai usar este template para renderizar a propriedade **imagens** do DTO *Jogo* porque seguimos uma convenção: pasta **jogo**, arquivo **imagens.ftl**.
> Mais detalhes no [Showcase](http://showcase.touchtec.com.br/dali-showcase/template/templates.action)

---

> #### Adicione o seguinte conteúdo ao arquivo
 
```html
<#-- Limitamos o numero de imagens em 5 para simplificar. -->

<#-- Declaração de variável -->
<#assign imagens = renderer.value />

<#-- Chamada de função -->
<@renderizaImagem imagem = imagens[0] name = renderer.name />

<@renderizaImagem imagem = imagens[1] name = renderer.name />

<@renderizaImagem imagem = imagens[2] name = renderer.name />

<@renderizaImagem imagem = imagens[3] name = renderer.name />

<@renderizaImagem imagem = imagens[4] name = renderer.name />

<#-- HTML dinâmico -->
<div>
    Total de imagens: <b>${imagens?size}</b>
</div>

<#-- Definição de função -->
<#macro renderizaImagem imagem = "" name = "">
    <div style="height: 50px; margin-bottom: 10px">
        <#-- O uso de taglibs é um pouco diferente do JSP: '<@t.' em vez de '<t:' -->
        <@t.fileupload name = name />

        <#-- condicional -->
        <#-- Por algum motivo obsuro não existe null no freemarker (e isso dá muita dor de cabeça) -->
        <#if imagem != "">
            <#-- ${} também funciona aqui (assim como em JSPs) -->
            <img src="${contextPath}/imagens?id=${imagem.name}"
                 style="height: 50px; width: 50px; vertical-align: top"
            />
        </#if>
    </div>
</#macro>

```

## @SearchClause

