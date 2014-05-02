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
	private static LocalDao localMockDao = null;
	private static LocalDao localHttpDao = null;
	
	public static EventoDao getEventoMockInstance() {
		if (eventoMockDao == null) {
			eventoMockDao = new EventoDaoMockImpl();
		}
		
		return eventoMockDao;
	}
	
	public static EventoDao getEventoHttpInstance() {
		if (eventoHttpDao == null) {
			eventoHttpDao = new EventoDaoHttpImpl();
		}
		
		return eventoHttpDao;
	}
	
	public static LocalDao getLocalMockInstance() {
		if (localMockDao == null) {
			localMockDao = new LocalDaoMockImpl();
		}
		
		return localMockDao;
	}
	
	public static LocalDao getLocalHttpInstance() {
		if (localHttpDao == null) {
			localHttpDao = new LocalDaoHttpImpl();
		}
		
		return localHttpDao;
	}
	
}
