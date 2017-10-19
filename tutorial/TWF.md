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
> [http://showcase.touchtec.com.br/twfc-showcase]()

# Componentes principais

## User Interface

## Leiaute

## Formulário

## Botões

# Ajax