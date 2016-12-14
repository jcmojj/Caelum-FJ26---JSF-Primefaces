package br.com.caelum.notasfiscais.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDeVidaListener implements PhaseListener{

	private static final long serialVersionUID = 1L;
	private Long tempoInicial;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		System.out.print("PE: AFTER " + event.getPhaseId());
		System.out.println(" - TIMELAPSE: " + ((double)(System.currentTimeMillis()-tempoInicial ))/1000 + "s");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		tempoInicial = System.currentTimeMillis();
		System.out.println("PE: BEFORE " + event.getPhaseId());		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

	
}
