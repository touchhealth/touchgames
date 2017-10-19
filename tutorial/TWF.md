# TWF e TWFC

- _Touch Web Framework_: Extensões para Struts (Actions)
- _Touch Web Framework Components_: componentes visuais (taglibs)

## Motivações
- HTML é verboso
- HTML é repetitivo
- HTML não é reaproveitável


# O que são Tag Libs?

São equivalentes a tags HTML, mas _cutomizáveis_ e feitas em _Java_.
Também são chamdas de _componentes_.

Escrevemos isto:
```html
<t:panel>
    <t:table list="%{desenvolvedoras}">
    ...
    </t:table>
</t:panel>
```

E gerado isto:
```html
<div ttype="panel" class=”panel”>
    <div class=”content”>
        <table>
            <thead>
                <tr>
                    <th>...</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>...</td>
                </tr>
                <tr>
                    <td>...</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
```

E depois isto:

![](img/twf1.png)

# Showcase

É onde consultamos a documentação dos componentes do TWFC

> #### Acesse o showcase e navegue por algumas páginas
> [Showcase](http://showcase.touchtec.com.br/twfc-showcase)

# Componentes principais
- User Interface
- Leiaute
    - panel, toolbar
    - tabbox
    - table
- Formulário
    - field, label
    - textinput
    - numberinput
    - datepicker
    - singleselect
    - multipleselect
    - fileupload
- Botões
    - ajaxbuton
    - submitbutton
    - jsbutton

# Ajax

O TWFC fornece algumas facilidades para executar requests **AJAX**

![](img/twf2.png)


```html
<t:ajaxbutton action="Desenvolvedoras!create.action" responseTarget="response" />

<div id="response"><!-- Atualizado por AJAX --></div>
```

> #### Suba a aplicação e acesse
> [/Desenvolvedoras.action]()  
> Abra o **Chrome Dev Tools** (F12)  
> Aba Network  
> Aperte o botão Criar  
> Clique na request e veja o **preview**: veja que apenas o trecho do formulário (e não a página inteira) é retornado.