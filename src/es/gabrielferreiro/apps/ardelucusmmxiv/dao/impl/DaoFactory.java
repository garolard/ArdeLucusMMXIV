/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;

/**
 * @author Gabriel
 *
 */
public class DaoFactory {

	private static EventoDao mockDao = null;
	private static EventoDao httpDao = null;
	
	public static EventoDao getMockInstance() {
		if (mockDao == null) {
			mockDao = new MockEventoDao();
		}
		
		return mockDao;
	}
	
	public static EventoDao getHttpInstance() {
		if (httpDao == null) {
			httpDao = new HttpEventoDao();
		}
		
		return httpDao;
	}
	
}
