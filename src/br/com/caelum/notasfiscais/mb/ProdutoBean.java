package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@RequestScoped
public class ProdutoBean {
	
	@Inject
	private ProdutoDao dao;
	@Inject
	private Produto produto = new Produto();
	private List<Produto> produtos;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void grava() {
//		ProdutoDao dao = new ProdutoDao();
		if (produto.getId() == null) {
			dao.adiciona(produto); System.out.println("teste adiciona");
		} else {
			dao.atualiza(produto); System.out.println("teste atualiza");
		}
		this.produto = new Produto(); // zera o valor do produto para poder
										// cadastrar um novo
		produtos = dao.listaTodos();
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos...");
//			produtos = new ProdutoDao().listaTodos();
			produtos = dao.listaTodos();
		}
		return produtos;
	}

	public Double getListaSoma() {
		Double total = 0.0;
		for (Produto produto : produtos) {
			total += produto.getPreco();
		}
		return total;
	}

	public void remove(Produto produto) {
//		ProdutoDao dao = new ProdutoDao();
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}

	public void cancelar() {
		this.produto.setNome("");
		this.produto.setDescricao("");
		this.produto.setPreco(0.0);
	}

}
