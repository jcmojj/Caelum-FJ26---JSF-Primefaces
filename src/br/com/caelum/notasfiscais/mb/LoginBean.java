package br.com.caelum.notasfiscais.mb;



import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @RequestScoped//@SessionScoped //@ManagedBean
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao dao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public String efetuaLogin(){
		//UsuarioDao dao = new UsuarioDao();
		boolean loginValido = dao.existe(this.usuario);
		if (loginValido){
			usuarioLogado.logar(usuario);
			return "produto?faces-redirect=true";
		}else{
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	public String logout(){
		usuarioLogado.deslogar();
		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
