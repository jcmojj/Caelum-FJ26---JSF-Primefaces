package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		FacesContext context = event.getFacesContext();
		// endereço acessado na navegador pelo usuario
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		// Verificacação
		if (!usuarioLogado.isLogado()) {
			NavigationHandler handler = context.getApplication()
					.getNavigationHandler();
			handler.handleNavigation(context, null, "login?faces-redirect=true");

		// Efetua renderização da tela - vai direto para a ultima fase do jsf
			context.renderResponse();
		}
	}// fim do afterPhase

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
