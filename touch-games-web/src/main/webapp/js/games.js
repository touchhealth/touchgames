function atualizaPreco(index){
	var quantidade = $t('quantidade['+index+']').getValue();
	var preco = $t('preco['+index+']').getValue();
	$t('total['+index+']').setValue( preco * quantidade);
}