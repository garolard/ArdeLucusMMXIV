/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service;

import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public interface EventoService extends Service<Evento, Integer> {

	void updateLocalDatabaseIfNeeded();
	void findByCategoryAsync(String categoryId, AsyncHandler handler);
	
}
