package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");
	
	
	@Produces @RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void destroyEntityManager(@Disposes EntityManager em){
		em.close();
	}
	

	@Produces @RequestScoped
	public FacesContext createContext(){
		return FacesContext.getCurrentInstance();
	}
	
		
	@Produces @RequestScoped
	public NavigationHandler createHandler(){
		return FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	}
	
}