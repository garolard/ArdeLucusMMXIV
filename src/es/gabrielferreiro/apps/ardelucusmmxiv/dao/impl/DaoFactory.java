/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;

/**
 * @author Gabriel
 *
 */
public class DaoFactory {

	private static EventoDao eventoMockDao = null;
	private static EventoDao eventoHttpDao = null;
	private static LocalDao localHttpDao = null;
	
	public static EventoDao getEventoMockInstance() {
		if (eventoMockDao == null) {
			eventoMockDao = new MockEventoDao();
		}
		
		return eventoMockDao;
	}
	
	public static EventoDao getEventoHttpInstance() {
		if (eventoHttpDao == null) {
			eventoHttpDao = new HttpEventoDao();
		}
		
		return eventoHttpDao;
	}
	
	public static LocalDao getLocalHttpInstance() {
		if (localHttpDao == null) {
			localHttpDao = new HttpLocalDao();
		}
		
		return localHttpDao;
	}
	
}
