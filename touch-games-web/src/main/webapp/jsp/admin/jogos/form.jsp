<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:t="http://www.touchtec.com.br/twfc-tags">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	
	<t:loadbundle var="i18n" basename="Names" />
	
	<t:panel id="jogoForm" cssClass="form">
		<t:title value="%{formTitle}"/>
		
		<input type="hidden" name="jogo.id" value="${jogo.id}"/>
		
		<t:set component="label" value="width:150px" property="style" />
		
		<t:tabbox>
			<t:tabpanel>
				<t:title>Geral</t:title>
				<t:field>
					<t:label value="%{i18n.Jogo.nome}"/>
					<t:textinput name="jogo.nome" value="%{jogo.nome}"/>
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.descricao}"/>
					<t:textinput template="textarea" name="jogo.descricao" value="%{jogo.descricao}"/>
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.genero}"/>
					<t:singleselect name="jogo.genero" value="%{jogo.genero}" enumList="br.com.touchtec.games.core.model.Genero"/>
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.desenvolvedora}"/>
					<t:singleselect name="jogo.desenvolvedora.id" value="%{jogo.desenvolvedora}" 
						list="%{desenvolvedoras}" optionLabel="nome"  optionValue="id"/>
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.plataformas}"/>
					<t:multipleselect name="jogo.plataformas" optionValueName="id" value="%{jogo.plataformas}"  
						list="%{plataformas}" optionLabel="nome" optionValue="id" />
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.dataLancamento}"/>
					<t:datepicker name="jogo.dataLancamento" value="%{jogo.dataLancamento}"/>
				</t:field>
			</t:tabpanel>
			
			<t:tabpanel>
				<t:title>Preço</t:title>
				
				<t:field>
					<t:label value="%{i18n.Jogo.preco}"/>
					<t:numberinput name="jogo.preco" value="%{jogo.preco}"/>
				</t:field>
				<t:field>
					<t:label value="%{i18n.Jogo.desconto}"/>
					<t:numberinput name="jogo.desconto" value="%{jogo.desconto}"/>
				</t:field>
			</t:tabpanel>
			
			<t:tabpanel>
				<t:title>Imagens</t:title>
				
				<c:forEach begin="0" end="4" var="index">
					<t:field>
						<t:label>Imagem ${index + 1}</t:label>
						<t:fileupload style="vertical-align: top" name="imagens[${index}]" />
						<c:if test="${jogo.imagens[index] != null}">
							<img src="${app}/imagens?id=${jogo.imagens[index].id}" width="40px"/>
						</c:if>
					</t:field>
				</c:forEach>
			</t:tabpanel>
		</t:tabbox>
		
		<t:toolbar>
			<t:jsbutton template="cancel" action="$('jogoForm').remove(); $t('list').open()"/>
			<t:submitbutton action="Jogos!save.action" template="save" primary="true"/>
		</t:toolbar>
	</t:panel>
	
</jsp:root>