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
