package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ViewScoped

public class NotaFiscalBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	
	
	@Inject
	NotaFiscalDao notaFiscalDao;
	@Inject
	ProdutoDao produtoDao;

	public void gravar(){
		this.notaFiscalDao.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
	}
	
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		// Populando o item com o produto que veio da tela pelo Id
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		// Pego o item populado e coloco na nota fiscal
		notaFiscal.getItens().add(item);
		// Fazendo a consistencia de duas vias para ficar ok na tela
		// No banco de dados a consistencia sera feita, mas somente depois de subir para o BD
		item.setNotaFiscal(notaFiscal);
		// zero o item para eu poder adicionar um novo
		item = new Item();
		idProduto = null;
	}
	
	// getters and setters
	public NotaFiscal getNotaFiscal(){
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}
