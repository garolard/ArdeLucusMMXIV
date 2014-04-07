/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

/**
 * @author Gabriel
 *
 */
public class DaoFactory {

	private static MockEventoDao mockDao = null;
	
	public static MockEventoDao getMockInstance() {
		if (mockDao == null) {
			mockDao = new MockEventoDao();
		}
		
		return mockDao;
	}
	
}
