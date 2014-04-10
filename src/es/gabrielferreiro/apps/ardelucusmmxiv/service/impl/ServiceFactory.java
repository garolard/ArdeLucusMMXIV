/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service.impl;

/**
 * @author Gabriel
 *
 */
public class ServiceFactory {

	private static EventoServiceImpl eventoService = null;
	
	public static EventoServiceImpl getEventoService() {
		if (eventoService == null) {
			eventoService = new EventoServiceImpl();
		}
		
		return eventoService;
	}
	
}
